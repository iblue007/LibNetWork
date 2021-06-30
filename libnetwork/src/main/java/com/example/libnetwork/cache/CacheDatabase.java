package com.example.libnetwork.cache;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.libnetwork.global.AppGlobals;

@Database(entities = {Cache.class}, version = 1)
public abstract class CacheDatabase extends RoomDatabase {

    private static final CacheDatabase database;

    static {
        database = Room.databaseBuilder(AppGlobals.getApplication(), CacheDatabase.class, "libnetwork_cache")
                .allowMainThreadQueries()//是否允许在主线程进行查询
                .build();
    }

    public static CacheDatabase get() {
        return database;
    }

    public abstract CacheDao getCache();
}
