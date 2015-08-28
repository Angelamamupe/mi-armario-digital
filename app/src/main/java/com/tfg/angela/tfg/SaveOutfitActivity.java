package com.tfg.angela.tfg;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * TODO
 *  1. Documentar bien y en inglés
 *  2. Casos de error: que pasa por ej si hacemos una foto, luego le damos a retomar foto, y
 *     cancelamos desde la camara? Le podemos dar a compartir en instagram y a retomar foto otra vez
 *     pero creo que puede que peten.
 *  3. Hacer mas bonita la interfaz.
 *  4. Guardar foto en alguna BBDD.
 */
public class SaveOutfitActivity extends AppCompatActivity {

    Button btTakePicture;
    Button btShareInstagram;
    ImageView ivPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_outfit);

        initUi();
    }

    private void initUi() {
        // TODO:
        // 1. Hacer los findview by id para todos los componentes que quieras usar en el codigo

        btTakePicture = (Button) findViewById(R.id.btTakePicture);
        btShareInstagram = (Button) findViewById(R.id.btShareInstagram);


        // 2. Poner onClickListeners en los botones a los metodos que nos interesen
        btTakePicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        btShareInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharePictureInInstagram(mCurrentPhotoPath);
            }
        });

        ivPreview = (ImageView) findViewById(R.id.ivPreview);
    }

    // Un numero cualquiera, se usa para que en onActivityResult se sepa a que metodo se llamo antes
    private static final int REQUEST_IMAGE_CAPTURE = 1;

    // Aqui estara guardada la Uri (URL interna) a la foto.
    private Uri mCurrentPhotoPath;

    // Este metodo crea un archivo en que guardar la foto de la camara. De nombre de archivo le pone
    // JPEG_fecha_hora_.jpg y guarda la uri en currentPhotoPath
    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = new File(
                storageDir + File.separator + imageFileName +   /* prefix */
                        ".jpg"         /* suffix */
                      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = Uri.fromFile(image);
        return image;
    }

    /*************************************
     * Button methods
     *************************************/

    private void takePicture() {
        // TODO Rellenar, repeat?
        // 1. Mandar un intent para hacer la foto
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // 2. Meter en el intent el nombre de archivo con el que se va a guardar la foto.
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Creación del archivo donde debe ir la foto
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error durante la creacion de la foto
                Toast.makeText(this, "Error inesperado", Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }
            // Continua solo si el archivo se ha creado correctamente
            if (photoFile != null) {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
                        Uri.fromFile(photoFile));
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private void retakePicture() {
        // 1. Borrar la foto actual y desactivar comparticion en instagram.

        File currentPicture = new File(mCurrentPhotoPath.getPath());
        if (currentPicture.exists()) {
            if (currentPicture.delete()) {
                Log.d("SaveOutfitActivity", "file Deleted :" + mCurrentPhotoPath);
            } else {
                Log.e("SaveOutfitActivity", "couldn't delete :" + mCurrentPhotoPath);
            }
        }
        // 2. Llamar a takePicture

        takePicture();
    }

    private void sharePictureInInstagram(Uri uri) {
        // TODO Rellenar
        Intent shareIntent = new Intent(Intent.ACTION_SEND);

        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shareIntent.putExtra(Intent.EXTRA_TEXT,"#hashtag-");
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setPackage("com.instagram.android");
        startActivity(shareIntent);

        // Broadcast the Intent.
        startActivity(shareIntent);
    }

    /*************************************
     * Result method
     *************************************/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Rellenar
        // 1. Comprobar que nos hayan mandado una foto. (si no, igual el usuario ha cancelado)
        // 2. Si si, mostrar una vista previa
        // 3.      Activar comparticion por instagram


        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            btTakePicture.setText("Repetir foto");
            btShareInstagram.setVisibility(View.VISIBLE);

            Glide.with(this).load(mCurrentPhotoPath).into(ivPreview);
        }

    }

}
