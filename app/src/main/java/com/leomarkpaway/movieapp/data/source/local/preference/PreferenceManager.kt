package com.leomarkpaway.movieapp.data.source.local.preference

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class PreferenceManager(context: Context, preferenceName: String) {
    // TODO make this accept all data type to save on pref
    // Set Data Store
    private val Context.dataStore by preferencesDataStore(preferenceName)
    private val dataStore = context.dataStore

    // Save the Boolean to DataStore
    suspend fun saveBoolean(preferenceKey: String, value: Boolean) {
        if (preferenceKey.isNotEmpty()) {
            val dataStoreKey = booleanPreferencesKey(preferenceKey)
            dataStore.edit { settings -> settings[dataStoreKey] = value }
        }
    }

    // Retrieve the Boolean from DataStore
    suspend fun getBoolean(preferenceKey: String): Boolean? {
        return if (preferenceKey.isNotEmpty()) {
            val dataStoreKey = booleanPreferencesKey(preferenceKey)
            val preferences = dataStore.data.first()
            preferences[dataStoreKey]
        } else null
    }

}