
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Cart {
    Map<Product, Integer> items = new HashMap<>();

    public void showProduct(){
        System.out.println("장바구니 목록 :");
        for (var itm : items.keySet()){
            System.out.println(itm.getName() + " : " + items.get(itm));
        }
    }

    public Map<String, Integer> showItemStream() {
        return items.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey().getName(), e -> e.getValue()));
    }
    public void addProduct(Product itm,int count){

//        //있으면 개수를 더하고, 처음 나왔으면 새로 추가한다.
//        if(items.containsKey(itm)){
//            items.put(itm, items.put(itm, items.get(itm) + count));
//        } else {
//            items.put(itm, count);
//        }

        // merge() 로 개선하기,
        items.merge(itm, count,(odd, newval) -> {return odd + newval;});
    }
    public void removeProduct(Product itm,int count){
//        if(items.containsKey(itm)){
//            int cnt = items.get(itm) - count;
//            if(cnt > 0) {
//                items.replace(itm, cnt);
//            }else{
//                items.remove(itm);
//            }
//        }

        // 있으면 개수를 빼고, 없으면 아무것도 하지 않는다.
        if(items.containsKey(itm) && items.get(itm) > 0){
            items.merge(itm, count, (odd, newval) -> {return odd - newval;});
//            items.put(itm, items.get(itm) - count);
        }
    }
}
