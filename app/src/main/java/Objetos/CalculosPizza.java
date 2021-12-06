package Objetos;

public class CalculosPizza {
    private String[] tipos = {"Pizza Napolitana", "Pizza Predilecta", "Pizza Vegana", "Pizza Selecta"};
    private int[] preciosPizza = {12500, 13800, 15600, 18600};
    private String[] ingr = {"Tocino", "Extra Queso", "Champiñón", "Salame", "Albahaca"};
    private int[] preciosIngr = {350, 500, 250, 300, 450};

    public CalculosPizza() {
    }

    public CalculosPizza(String[] tipos, int[] preciosPizza, String[] ingr, int[] preciosIngr) {
        this.tipos = tipos;
        this.preciosPizza = preciosPizza;
        this.ingr = ingr;
        this.preciosIngr = preciosIngr;
    }

    public String[] getTipos() {
        return tipos;
    }

    public void setTipos(String[] tipos) {
        this.tipos = tipos;
    }

    public int[] getPreciosPizza() {
        return preciosPizza;
    }

    public void setPreciosPizza(int[] preciosPizza) {
        this.preciosPizza = preciosPizza;
    }

    public String[] getIngr() {
        return ingr;
    }

    public void setIngr(String[] ingr) {
        this.ingr = ingr;
    }

    public int[] getPreciosIngr() {
        return preciosIngr;
    }

    public void setPreciosIngr(int[] preciosIngr) {
        this.preciosIngr = preciosIngr;
    }

    public int SumaPizzaIngre(String pizza, String ingrediente){
        int sum = 0;
        for(int i = 0; i < tipos.length; i++){
            if(tipos[i].equals(pizza)){
                sum = sum + preciosPizza[i];
            }
        }
        for(int i = 0; i < ingr.length; i++){
            if(ingr[i].equals(ingrediente)){
                sum = sum + preciosIngr[i];
            }
        }
        return sum;
    }
}
