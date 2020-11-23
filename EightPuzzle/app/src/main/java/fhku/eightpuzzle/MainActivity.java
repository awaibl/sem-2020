package fhku.eightpuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    protected static final String TAG = "MAIN";

    protected ImageView image1;
    protected ImageView image2;
    protected ImageView image3;
    protected ImageView image4;
    protected ImageView image5;
    protected ImageView image6;
    protected ImageView image7;
    protected ImageView image8;
    protected ImageView image9;
    protected ImageView[] images = new ImageView[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images[0] = findViewById(R.id.image_1);
        images[1] = findViewById(R.id.image_2);
        images[2] = findViewById(R.id.image_3);
        images[3] = findViewById(R.id.image_4);
        images[4] = findViewById(R.id.image_5);
        images[5] = findViewById(R.id.image_6);
        images[6] = findViewById(R.id.image_7);
        images[7] = findViewById(R.id.image_8);
        //images[8] = findViewById(R.id.image_9);

        prepareGrid();

        //Übung 1
        images[0].setOnTouchListener(this);
        images[1].setOnTouchListener(this);
        images[2].setOnTouchListener(this);
        images[3].setOnTouchListener(this);
        images[4].setOnTouchListener(this);
        images[5].setOnTouchListener(this);
        images[6].setOnTouchListener(this);
        images[7].setOnTouchListener(this);
        //images[8].setOnTouchListener(this);

        images[0].setEnabled(true);
        images[1].setEnabled(true);
        images[2].setEnabled(true);
        images[3].setEnabled(true);
        images[4].setEnabled(true);
        images[5].setEnabled(true);
        images[6].setEnabled(true);
        images[7].setEnabled(true);
        //images[8].setEnabled(true);
        

    }

    public void openCamera(View view) {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    public void prepareGrid() {
        File picture = new File(getFilesDir(), "my_picture.jpg");
        if (picture.isFile()) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(picture));
                Bitmap[] grid = gridImage(cropImage(bitmap));

                //Übung 1
                for (int i = 0; i < images.length; i++) {
                    images[i].setImageBitmap(grid[i]);
                }

            } catch (Exception e) {
                Log.e(TAG, e.getMessage(), e);
            }
        }

    }

    public Bitmap[] gridImage(Bitmap source) {
        Bitmap[] grid = new Bitmap[9];

        int size = source.getWidth() / 3;

        int i = 0;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                int x = col * size;
                int y = row * size;
                grid[i] = Bitmap.createBitmap(source, x, y, size, size);
                i++;
            }
        }

        return grid;
    }

    //Übung 1
   /* public Bitmap[] cut (Bitmap source){
        Bitmap [] grid = new Bitmap[9];

        int size = source.getHeight() / 3;
        int i = 0;

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                grid[i] = Bitmap.createBitmap(source,col * size, row * size, size, size);
                i++;
            }
        }

        return grid;

    }*/

    public Bitmap cropImage(Bitmap source) {
        int x = 0;
        int y = 0;
        int size;

        Matrix matrix = new Matrix();
        matrix.postRotate(90);

        if (source.getHeight() > source.getWidth()) {
            size = source.getWidth();
            y = (source.getHeight() - size) / 2;
        } else {
            size = source.getHeight();
            x = (source.getWidth() - size) / 2;
        }

        return Bitmap.createBitmap(source, x, y, size, size, matrix, true);
    }

    //Übung 2
    public int[] move(int pos){

        int[] moveable = new int[0];

        if(pos == 1){
            images[1].setEnabled(false);
            images[3].setEnabled(false);

            moveable = new int[]{2, 4};

            return moveable;
        }else if(pos == 2){
            images[0].setEnabled(false);
            images[2].setEnabled(false);
            images[4].setEnabled(false);

            moveable = new int[]{1, 3, 5};

           
        }else if(pos == 3){
            images[2].setEnabled(false);
            images[6].setEnabled(false);

            moveable = new int[]{2, 6};

        }


        return moveable;

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}