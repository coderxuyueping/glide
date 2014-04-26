package com.bumptech.glide.samples.flickr;

import android.content.Context;
import com.bumptech.glide.loader.bitmap.model.Cache;
import com.bumptech.glide.loader.bitmap.model.GenericLoaderFactory;
import com.bumptech.glide.loader.bitmap.model.ModelLoader;
import com.bumptech.glide.loader.bitmap.model.ModelLoaderFactory;
import com.bumptech.glide.loader.bitmap.model.stream.BaseUrlLoader;
import com.bumptech.glide.samples.flickr.api.Api;
import com.bumptech.glide.samples.flickr.api.Photo;

import java.io.InputStream;
import java.net.URL;

/**
 * An implementation of ModelStreamLoader that leverages the StreamOpener class and the ExecutorService backing the
 * ImageManager to download the image and resize it in memory before saving the resized version
 * directly to the disk cache.
 */
public class FlickrModelLoader extends BaseUrlLoader<Photo> {

    public static class Factory implements ModelLoaderFactory<Photo, InputStream> {
        private final Cache<URL> modelCache = new Cache<URL>();

        @Override
        public ModelLoader<Photo, InputStream> build(Context context, GenericLoaderFactory factories) {
            return new FlickrModelLoader(context, modelCache);
        }

        @Override
        public void teardown() {
        }
    }

    public FlickrModelLoader(Context context, Cache<URL> modelCache) {
        super(context, modelCache);
    }

    @Override
    protected String getUrl(Photo model, int width, int height) {
        return Api.getPhotoURL(model, width, height);
    }

    @Override
    public String getId(Photo model) {
        return model.id;
    }
}
