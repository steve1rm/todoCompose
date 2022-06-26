package me.androidbox.data.repository.imp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import me.androidbox.data.util.Constant.PREFERENCE_STORE_KEY
import me.androidbox.data.util.Constant.PREFERENCE_STORE_NAME
import me.androidbox.domain.repository.DataStoreRepository
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCE_STORE_NAME)

class DataStoreRepositoryImp @Inject constructor(
    @ApplicationContext private val context: Context) : DataStoreRepository {

    private val dataStore = context.dataStore
    private object PreferenceKey {
        val sortKey = stringPreferencesKey(PREFERENCE_STORE_KEY)
    }

    override suspend fun persistSortState(priority: String) {
        dataStore.edit { preference ->
            preference[PreferenceKey.sortKey] = priority
        }
    }

    override fun readSortState(): Flow<String> {
        return dataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw IOException()
                }
            }
            .map { preference ->
                preference[PreferenceKey.sortKey] ?: "NONE"
            }
    }
}