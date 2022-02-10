package com.devpub.cleanmvvm.util.bindingadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.viewbinding.ViewBinding
import com.devpub.cleanmvvm.ui.common.binding.BindingSheetDialog
import com.devpub.cleanmvvm.ui.common.binding.BindingActivity
import com.devpub.cleanmvvm.ui.common.binding.BindingFragment
import java.lang.reflect.ParameterizedType

internal fun <V : ViewBinding> Class<*>.getBinding(layoutInflater: LayoutInflater): V {
    return try {
        @Suppress("UNCHECKED_CAST")
        getMethod(
            "inflate",
            LayoutInflater::class.java
        ).invoke(null, layoutInflater) as V
    } catch (ex: Exception) {
        throw RuntimeException("The ViewBinding inflate function has been changed.", ex)
    }
}

internal fun <V : ViewBinding> Class<*>.getBinding(
    layoutInflater: LayoutInflater,
    container: ViewGroup? = null,
    attachToRoot : Boolean = false
): V {
    return try {
        @Suppress("UNCHECKED_CAST")
        getMethod(
            "inflate",
            LayoutInflater::class.java,
            ViewGroup::class.java,
            Boolean::class.java
        ).invoke(null, layoutInflater, container, attachToRoot) as V
    } catch (ex: Exception) {
        throw RuntimeException("The ViewBinding inflate function has been changed.", ex)
    }
}

internal fun Class<*>.checkMethod(): Boolean {
    return try {
        getMethod(
            "inflate",
            LayoutInflater::class.java
        )
        true
    } catch (ex: Exception) {
        false
    }
}

internal fun Any.findClass(): Class<*> {
    var javaClass: Class<*> = this.javaClass
    var result: Class<*>? = null
    while (result == null || !result.checkMethod()) {
        result = (javaClass.genericSuperclass as? ParameterizedType)
            ?.actualTypeArguments?.firstOrNull {
                if (it is Class<*>) {
                    it.checkMethod()
                } else {
                    false
                }
            } as? Class<*>
        javaClass = javaClass.superclass
    }
    return result
}

inline fun <reified V : ViewBinding> ViewGroup.toBinding(): V {
    return V::class.java.getMethod(
        "inflate",
        LayoutInflater::class.java,
        ViewGroup::class.java,
        Boolean::class.java
    ).invoke(null, LayoutInflater.from(context), this, false) as V
}

internal fun <V : ViewDataBinding> BindingActivity<V>.getBinding(): V {
    return findClass().getBinding(layoutInflater)
}

internal fun <V : ViewDataBinding> BindingFragment<V>.getBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
): V {
    return findClass().getBinding(inflater, container)
}

internal fun <V : ViewDataBinding> BindingSheetDialog<V>.getBinding(
    inflater: LayoutInflater,
    container: ViewGroup?
): V {
    return findClass().getBinding(inflater, container)
}

internal fun <V : ViewDataBinding> ViewGroup.getBinding(
    inflater: LayoutInflater,
    container: ViewGroup? = null,
    attachToRoot : Boolean = false
): V {
    return findClass().getBinding(inflater, container, attachToRoot)
}

internal val Context.inflater: LayoutInflater get() = LayoutInflater.from(this)