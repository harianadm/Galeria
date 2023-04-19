package radaelli.chagas.adami.harian.galeria.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;
//guardando o endereco uri da foto, obtendo os itens da lista e setando o endereco uri no modelview
public class NewItemActivityViewModel extends ViewModel {
    Uri selectPhotoLocation = null;

    public Uri getSelectedPhotoLocation(){
        return selectPhotoLocation;
    }

    public void setSelectPhotoLocation(Uri selectPhotoLocation){
        this.selectPhotoLocation = selectPhotoLocation;
    }
}
