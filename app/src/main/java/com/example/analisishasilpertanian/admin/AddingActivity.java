package com.example.analisishasilpertanian.admin;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.analisishasilpertanian.ModelAnalysis;
import com.example.analisishasilpertanian.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static com.example.analisishasilpertanian.BitmapConverter.BitMapToString;

public class AddingActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    int bitmap_size = 60; // range 1 - 100
    private ImageView JPEG;
    private Bitmap decoded;
    private EditText name, commodity, results, maxResults, age;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        JPEG = findViewById(R.id.imageView);
        name = findViewById(R.id.tv_name);
        commodity = findViewById(R.id.tv_comodity);
        results = findViewById(R.id.tv_results);
        maxResults = findViewById(R.id.tv_max_results);
        age = findViewById(R.id.tv_age);

        Button submit = findViewById(R.id.submit);
        Button button = findViewById(R.id.button);

        databaseReference = FirebaseDatabase.getInstance().getReference(ModelAnalysis.MODELANALYSIS);
        button.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
        });
        submit.setOnClickListener(view -> {
            String postImage = BitMapToString(decoded);
            String id = databaseReference.push().getKey();
            ModelAnalysis analysis = new ModelAnalysis(id, name.getText().toString(), commodity.getText().toString(), results.getText().toString(), maxResults.getText().toString(), age.getText().toString(), postImage);
            assert id != null;
            databaseReference.child(id).setValue(analysis);
            Toast.makeText(getBaseContext(), "Berhasil input", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                setToImageView(getResizedBitmap(bitmap, 512));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setToImageView(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, bitmap_size, bytes);
        decoded = BitmapFactory.decodeStream(new ByteArrayInputStream(bytes.toByteArray()));
        Glide.with(this).load(decoded).into(JPEG);
    }

    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float) width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }

}
