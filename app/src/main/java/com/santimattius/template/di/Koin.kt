package com.santimattius.template.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.module.Module

/**
 * start or load koin
 */
fun initKoin(app: Application, init: KoinApplicationWrapper.() -> Unit) {
    val wrapper = KoinApplicationWrapper(app)
    wrapper.init()
    with(wrapper) {
        val koinContext = GlobalContext.getOrNull()
        if (koinContext == null) {
            startKoin {
                androidContext(application)
                modules(modules)
            }
        } else {
            loadKoinModules(modules)
        }
    }
}

class KoinApplicationWrapper internal constructor(
    val application: Application,
    var modules: MutableList<Module> = mutableListOf(),
) {

    fun module(module: Module) {
        modules.add(module)
    }

    fun module(module: List<Module>) {
        modules.addAll(module)
    }
}