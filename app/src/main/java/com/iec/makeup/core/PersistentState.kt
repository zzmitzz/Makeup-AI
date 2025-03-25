package com.iec.makeup.core

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

val Context.appDataStore: DataStore<Preferences> by preferencesDataStore("app")


object PreferenceKeys {
    val USER_NAME = stringPreferencesKey("user_name")
    val USER_PASSWORD = stringPreferencesKey("user_password")
}


interface DataStoreInterface {
    fun saveKey(key: Preferences.Key<String>, value: String)
    fun readKey(key: Preferences.Key<String>): Flow<String?>
    fun clearKey(vararg keys: Preferences.Key<String>)
}




class PersistentState(private var context: Context) : com.iec.makeup.base.DataStoreInterface {
    companion object {
        @SuppressLint("StaticFieldLeak")
        private var INSTANCE: com.iec.makeup.base.PersistentState? = null
        fun getInstance(context: Context): com.iec.makeup.base.PersistentState {
            return com.iec.makeup.base.PersistentState.Companion.INSTANCE ?: synchronized(this) {
                val instance = com.iec.makeup.base.PersistentState(context)
                com.iec.makeup.base.PersistentState.Companion.INSTANCE = instance
                instance
            }
        }
    }

    private val dataStore = context.appDataStore
    private val scope = CoroutineScope(Dispatchers.IO) + SupervisorJob() + CoroutineExceptionHandler{
        _, exception -> Log.d("DataStoreImplement", exception.toString())
    }
    override fun saveKey(key: Preferences.Key<String>, value: String) {
        scope.launch {
            dataStore.edit {
                it[key] = value
            }
        }
    }

    override fun readKey(key: Preferences.Key<String>): Flow<String?> {
        return dataStore.data.map {
            it[key]
        }
    }

    override fun clearKey(vararg keys: Preferences.Key<String>) {
        scope.launch {
            keys.forEach { key ->
                dataStore.edit { it.remove(key) }
            }
        }
    }

}