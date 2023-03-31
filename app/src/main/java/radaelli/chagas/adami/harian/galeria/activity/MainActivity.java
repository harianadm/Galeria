package radaelli.chagas.adami.harian.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import radaelli.chagas.adami.harian.galeria.R;
import radaelli.chagas.adami.harian.galeria.model.MyItem;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST =1;
    List<MyItem> itens = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //verificando se a condicao de retorno foi cumprida
        if(requestCode == NEW_ITEM_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                //se sim o my item guarda os dados
                MyItem myItem = new MyItem();
                myItem.title = data.getStringExtra("title");
                myItem.description = data.getStringExtra("description");
                myItem.photo = data.getData();
                //e adiciona na lista de itens
                itens.add(myItem);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtendo o botao fab
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddNewItem);
        //registrando um ouvidor de cliques
        fabAddItem.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //intent explicito para navegar
                Intent i = new Intent(MainActivity.this, NewItemActivity.class);
                //executando o intent
                startActivityForResult(i, NEW_ITEM_REQUEST);
            }
        });
    }
}