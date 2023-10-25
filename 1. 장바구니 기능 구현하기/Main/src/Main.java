
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

import java.util.HashMap;
import java.util.Map;



public class Main {
    // HashSet을 이용해서 상품 목록 만들기
    public static void main(String[] args) {
        // 상품 목록 생성
        Set<Product> itmsSet = new HashSet<>();


        // 하위과제 csv 파일로 상품 클래스를 생성하여 상품목록에 넣는다.
        String csvFile = "C:\\Users\\dlqhd\\OneDrive\\Desktop\\java\\shopping\\Main\\products.csv";

        String line;

        //BuffredReader => InputStreamReader -> FileInputStream
        try(BufferedReader br = new BufferedReader(new FileReader(csvFile, Charset.forName("EUC-KR")))) {
            // 첫 번째 헤더 한줄 무시
            br.readLine(); // 키, 상품, 가격

            while ((line = br.readLine()) != null) {
                line = URLDecoder.decode(line, StandardCharsets.UTF_8.toString());
                StringTokenizer st = new StringTokenizer(line,",");
                Product itm = new Product(Integer.parseInt(st.nextToken()), st.nextToken(), Integer.parseInt(st.nextToken()));
                itmsSet.add(itm);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }



//        // 상품 목록 생성
//        Set<Product> itmsSet = new HashSet<>();
//
//        // 상품 클래스를 생성하여 상품목록에 넣는다.
//        itmsSet.add(new Product(0, "우유", 1500));
//        itmsSet.add(new Product(1, "감자", 500));
//        itmsSet.add(new Product(2, "고구마", 1000));

        // 상품 목록 확인
        System.out.println("고유한 상품 목록:");
        for (Product product : itmsSet) {
            System.out.println(product.getName() + " : " + product.getPrice());
        }
        System.out.println("----------------------");

        // 장바구니 생성
        Cart myCart = new Cart();

        // 상품을 장바구니에 추가
        Product[] products = itmsSet.toArray(new Product[0]);
        myCart.addProduct(products[0], 3); // 감자 3개
        myCart.addProduct(products[1], 2); // 우유 2개
        myCart.addProduct(products[2], 1); // 감자 1개

        myCart.addProduct(products[2], 1); // 감자 1개 추가하기

        System.out.println(" <상품 추가 후 장바구니 목록> ");
        myCart.showProduct();
        System.out.println("----------------------");

        // 상품을 장바구니에서 제거
        myCart.removeProduct(products[0], 1); // 감자 1개 빼기
        myCart.removeProduct(products[1], 1); // 우유 1개 빼기

        // 장바구니에 현재 담긴 상품들을 출력 (상품이름, 각 상품의 갯수)
        System.out.println(" <상품 제거 후 장바구니 목록> ");
        myCart.showProduct();
        System.out.println("----------------------");

        System.out.println(myCart.showItemStream());
    }
}