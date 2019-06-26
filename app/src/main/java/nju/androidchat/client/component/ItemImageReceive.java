package nju.androidchat.client.component;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.StyleableRes;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

import nju.androidchat.client.R;

public class ItemImageReceive extends LinearLayout {
    @StyleableRes
    int index0 = 0;

    private TextView textView;
    private ImageView imageView;
    private Context context;
    private UUID messageId;
    private OnRecallMessageRequested onRecallMessageRequested;


    public ItemImageReceive(Context context, String text, UUID messageId) {
        super(context);
        this.context = context;
        inflate(context, R.layout.item_text_receive, this);

        StrictMode.setThreadPolicy(new
                StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
        StrictMode.setVmPolicy(
                new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
        Bitmap bitmap = getImage(text);

        this.imageView = findViewById(R.id.chat_item_content_text);
        this.imageView.setImageBitmap(bitmap);
       // this.textView = findViewById(R.id.chat_item_content_text);
        this.messageId = messageId;
        //setText(text);
    }

    public static Bitmap getImage(String url) {
        Bitmap bitmap = null;
        try {
            URL url2 = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) url2.openConnection();
            conn.setConnectTimeout(6000);//设置超时
            conn.setDoInput(true);
            conn.setUseCaches(false);//不缓存
            conn.connect();
            InputStream is = conn.getInputStream();//获得图片的数据流
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    public void init(Context context) {

    }

    public String getText() {
        return textView.getText().toString();
    }

    public void setText(String text) {
        textView.setText(text);
    }
}
