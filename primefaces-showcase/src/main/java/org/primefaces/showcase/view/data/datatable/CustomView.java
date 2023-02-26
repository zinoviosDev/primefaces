package org.primefaces.showcase.view.data.datatable;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.SortOrder;
import org.primefaces.showcase.domain.Item;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named("dtCustomView")
@ViewScoped
public class CustomView implements Serializable  {

    private List<Item> list;

    private DataTable table;

    private FacesContext context = FacesContext.getCurrentInstance();

    @PostConstruct
    public void init() {
        list = new ArrayList<>();
        list.add(new Item(1, "Category 1", "Item 1", 10.0, 2));
        list.add(new Item(2, "Category 1", "Item 2", 15.0, 1));
        list.add(new Item(3, "Category 2", "Item 3", 20.0, 3));
        list.add(new Item(4, "Category 2", "Item 4", 5.0, 5));
        list.add(new Item(5, "Category 3", "Item 5", 8.0, 4));
        list.add(new Item(6, "Category 3", "Item 6", 12.0, 2));
    }

    // Getter and Setter for list
    public List<Item> getList() {
        return list;
    }

    public void setList(List<Item> list) {

        this.list = list;
    }

    public int categorySort(Item item1, Item item2) {
        String sortField = context.getExternalContext().getRequestParameterMap().get("form:datatableId_sortKey");
        SortOrder sortOrder = SortOrder.of(context.getExternalContext().getRequestParameterMap().get("form:datatableId_sortDir"));

        if (item1 == null && item2 == null) {
            return 0;
        }
        if (item1 == null) {
            return -1;
        }
        if (item2 == null) {
            return 1;
        }
        if (item1.getCategory() == null && item2.getCategory() == null) {
            return 0;
        }
        if (item1.getCategory() == null) {
            return -1;
        }
        if (item2.getCategory() == null) {
            return 1;
        }
        int result =  item1.getCategory().compareTo(item2.getCategory());
        result = (sortOrder == SortOrder.DESCENDING) ? -result : result;
        /*if (result == 0) {
            int result2 = -3;
            if (item1.getName() == null && item2.getName() == null) {
                result2 = 0;
            }
            else if (item1.getName() == null) {
                result2 = -1;
            }
            else if (item2.getName() == null) {
                result2 = 1;
            }
            else {
                result2 = item1.getName().compareTo(item2.getName());
            }
            result = (sortOrder == SortOrder.DESCENDING) ? -result2 : result2;
        }*/
        return result;
    }

    public DataTable getTable() {
        return table;
    }

    public void setTable(DataTable table) {
        this.table = table;
    }
}
