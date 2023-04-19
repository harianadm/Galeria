package radaelli.chagas.adami.harian.galeria.model;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;
//guardando a lista de itens e pegando ela com o metodo get
public class MainActivityViewModel extends ViewModel {
    List<MyItem> itens = new ArrayList<>();

    public List<MyItem> getItens(){
        return itens;
    }
}
