package radaelli.chagas.adami.harian.galeria.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import radaelli.chagas.adami.harian.galeria.R;
import radaelli.chagas.adami.harian.galeria.activity.MainActivity;
import radaelli.chagas.adami.harian.galeria.model.MyItem;

public class MyAdapter extends RecyclerView.Adapter{
    MainActivity mainActivity;
    List<MyItem> itens;

    public MyAdapter(MainActivity mainActivity, List<MyItem> itens){
        this.mainActivity = mainActivity;
        this.itens = itens;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        //obtendo um inflador de layouts
        LayoutInflater inflater = LayoutInflater.from(mainActivity);
        //usando o inflador para criar os elementos de interface e guardando eles dentro de um view
        View v = inflater.inflate(R.layout.item_list,parent,false);
        //guardando a view dentro do objeto MyViewHolder
        return new MyViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position){
        //obtendo o item para preencher o UI
        MyItem myItem = itens.get(position);
        //obtendo o objeto view que esta dentro do viewHolder
        View v = holder.itemView;
        //preenchendo o UI com os dados do item
        ImageView imvphoto = v.findViewById(R.id.imvPhoto);
        imvphoto.setImageURI(myItem.photo);
        TextView tvTitle = v.findViewById(R.id.tvTitle);
        tvTitle.setText(myItem.title);
        TextView tvDesc = v.findViewById(R.id.tvDesc);
        tvDesc.setText(myItem.description);
    }
    @Override
    public int getItemCount(){
        return itens.size();
    }
}
