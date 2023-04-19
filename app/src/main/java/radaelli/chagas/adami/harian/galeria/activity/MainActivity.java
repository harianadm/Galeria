package radaelli.chagas.adami.harian.galeria.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import radaelli.chagas.adami.harian.galeria.R;
import radaelli.chagas.adami.harian.galeria.adapter.MyAdapter;
import radaelli.chagas.adami.harian.galeria.model.MainActivityViewModel;
import radaelli.chagas.adami.harian.galeria.model.MyItem;
import radaelli.chagas.adami.harian.galeria.util.Util;

public class MainActivity extends AppCompatActivity {

    static int NEW_ITEM_REQUEST = 1;

    MyAdapter myAdapter;

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
        //obtendo o recycleView
        RecyclerView rvItens = findViewById(R.id.rvItens);

        MainActivityViewModel vm = new ViewModelProvider( this ).get(MainActivityViewModel.class);
        List<MyItem> itens = vm.getItens();

        //criando o adapter e setando no recycle view
        myAdapter = new MyAdapter(this,itens);

        rvItens.setAdapter(myAdapter);

        //indicando que nao ha variacao de tamanho entre os itens da lista

        rvItens.setHasFixedSize(true);
        //criando um gerenciador de layout do tipo linear e setando no RecycleView

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rvItens.setLayoutManager(layoutManager);

        //criando um decorador para a lista, que é uma linha separando cada item
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(rvItens.getContext(), DividerItemDecoration.VERTICAL);

        //setando o decorador no RecycleView
        rvItens.addItemDecoration(dividerItemDecoration);
    }


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
                Uri selectedPhotoUri = data.getData();
                // usando a funcao da classe util para carregar a imagem e guardar dentro de um bitmap
                //e depois guardando o bitmap da imagem dentro de um objeto MyItem
                try{
                    Bitmap photo = Util.getBitmap(MainActivity.this, selectedPhotoUri, 100, 100);
                    myItem.photo = photo;
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                MainActivityViewModel vm = new ViewModelProvider( this ).get(MainActivityViewModel.class);
                List<MyItem> itens = vm.getItens();

                //e adiciona na lista de itens
                itens.add(myItem);
                //notificando o adapter e exibindo o novo itens
                myAdapter.notifyItemInserted(itens.size()-1);
            }
        }
    }



}