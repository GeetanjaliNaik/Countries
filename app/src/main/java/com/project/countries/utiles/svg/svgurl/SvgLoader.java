package com.project.countries.utiles.svg.svgurl;

import android.app.Activity;
import android.net.Uri;
import android.widget.ImageView;

import java.io.File;

/**
 * Created by Geeta on 07/02/19.
 */
public class SvgLoader {

    private static SvgLoader instances;

    private SvgParser svgParser;
    private Activity activity;

    public static SvgLoader pluck() {
        if (instances == null)
            instances = new SvgLoader();
        return instances;
    }

    public SvgLoader with(Activity activity){
        this.activity = activity;
        svgParser = new SvgParser(activity);
        return instances;
    }

    public void close(){
        svgParser.clearCache();
    }

    public SvgLoader setPlaceHolder(int placeHolderLoading, int placeHolderError){
        svgParser.setPlaceHolder(placeHolderLoading, placeHolderError);
        return instances;
    }

    public SvgLoader load(String url, ImageView imageView){
        Uri uri = Uri.parse(url);
        svgParser.loadImage(uri, imageView);
        return instances;
    }
    public SvgLoader loadfile(File url, ImageView imageView){
        Uri uri = Uri.fromFile(url);
        svgParser.loadImagefile(uri, imageView);
        return instances;
    }
}
