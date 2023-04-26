package radaelli.chagas.adami.harian.galeria.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import radaelli.chagas.adami.harian.galeria.R;
import radaelli.chagas.adami.harian.galeria.model.NewItemActivityViewModel;

public class NewItemActivity extends AppCompatActivity {

    static int PHOTO_PICKER_REQUEST = 1;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        NewItemActivityViewModel vm = new ViewModelProvider( this ).get(NewItemActivityViewModel.class);

        Uri selectedPhotoLocation = vm.getSelectedPhotoLocation();
        if(selectedPhotoLocation != null){
            ImageView imvfotoPreview = findViewById(R.id.imvPhotoPreview);
            imvfotoPreview.setImageURI(selectedPhotoLocation);
        }

        //obtendo o img button
        ImageButton imgCl = findViewById(R.id.imbCl);
        //ouvidor de cliques
        imgCl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View V){
                //abertura da galeria para selecao de foto

                //intent impicito
                Intent photoPickerIntent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                //definindo que so quero pegar documentos com mimetype image/*
                photoPickerIntent.setType("image/*");
                //executando o intent
                startActivityForResult(photoPickerIntent, PHOTO_PICKER_REQUEST);
            }
        });
        ///obtendo o botao
        Button btnAddItem = findViewById(R.id.btnAddItem);
        //setando o ouvidor de cliques
        btnAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //verificando se os campos estao preenchidos

                NewItemActivityViewModel vm = new ViewModelProvider( NewItemActivity.this ).get(NewItemActivityViewModel.class);
                Uri selectedPhotoLocation = vm.getSelectedPhotoLocation();

                if(selectedPhotoLocation == null){
                    Toast.makeText(NewItemActivity.this, "É necessário selecionar uma imagem!", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etTitle = findViewById(R.id.etTitle);
                String title = etTitle.getText().toString();
                if(title.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir um titulo", Toast.LENGTH_LONG).show();
                    return;
                }
                EditText etDesc = findViewById(R.id.etDesc);
                String description = etDesc.getText().toString();
                if(description.isEmpty()){
                    Toast.makeText(NewItemActivity.this, "É necessário inserir uma descricao", Toast.LENGTH_LONG).show();
                    return;
                }
                //setando uma intent que servira para enviar os dados
                Intent i = new Intent();
                //setando o uri dentro da intent
                i.setData(selectedPhotoLocation);
                //setando o titulo na intent
                i.putExtra("title", title);
                //setando o descricao na intent
                i.putExtra("description", description);
                //indicando o resultado da activity
                setResult(Activity.RESULT_OK,i);
                //finalizando a activity
                finish();
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        // verificando se o requestCode é igual ao item com id photo_picker_request
        if (requestCode == PHOTO_PICKER_REQUEST) {
            //verificando se o resultCode é um codigo de sucesso
            if(resultCode == Activity.RESULT_OK){
                Uri photoSelected = data.getData();
                //obtendo o Uri da imagem escolhida
                ImageView imgPhotoPreview = findViewById(R.id.imvPhotoPreview);
                //setando o uri na imgPhotoPreview
                imgPhotoPreview.setImageURI(photoSelected);

                NewItemActivityViewModel vm = new ViewModelProvider( this).get(NewItemActivityViewModel.class);
                vm.setSelectPhotoLocation(photoSelected);
            }
        }
    }

}