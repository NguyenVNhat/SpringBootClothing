package com.example.NguyenVanNhat.Service;

import com.example.NguyenVanNhat.Models.*;
import com.example.NguyenVanNhat.Repository.ProductRespone;
import com.example.NguyenVanNhat.Repository.ResponeObject;
import com.example.NguyenVanNhat.Request.ColorRequest;
import com.example.NguyenVanNhat.Request.ProducUpdateRequest;
import com.example.NguyenVanNhat.Request.ProductRequest;
import com.example.NguyenVanNhat.Response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoriesRepository categoriesRepository;

    @Autowired
    private SizeRepository sizeRepository;

    @Autowired
    private ColorRepository colorRepository;

    @Autowired
    private StyleRepository styleRepository;

    @Autowired
    private ReviewProductRepository reviewProductRepository;

    @Autowired
    private ImageProductRepository imageProductRepository;

    @Autowired
    private RateProductRepository rateProductRepository;


    public ResponseEntity<ResponeObject> getall(){
        if( productRepository.findAll() == null){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.findAll()){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size size : product.getSizes()){
                    listSize.add(size.getSize());
                }
                for(Color color : product.getColors()){
                    listColor.add(color.getColor());
                }
                for(Style style : product.getStyles()){
                    listStyle.add(style.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                for (ReviewProduct reviewProduct : reviewProductRepository.findByproduct_id(product.getId())) {
                    listReview.add(reviewProduct.getContent());
                }
                for (ImageProduct imageProduct : imageProductRepository.findByproduct_id(product.getId())) {
                    listImage.add(imageProduct.getImage());
                }

                try{
                    rate = productRepository.getAverageRating(product.getId());
                }catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespone.setListReview(listReview);

                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }

    public ResponseEntity<ResponeObject> getbycategoryid(int categoryid){
        if( productRepository.findBycategories_id(categoryid) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.findBycategories_id(categoryid)){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size size : product.getSizes()){
                    listSize.add(size.getSize());
                }
                for(Color color : product.getColors()){
                    listColor.add(color.getColor());
                }
                for(Style style : product.getStyles()){
                    listStyle.add(style.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> getbycolorid(int colorid){

        Color color = colorRepository.findByid(colorid);
        if( productRepository.findBycolors(color) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.findBycolors(color)){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size size : product.getSizes()){
                    listSize.add(size.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style style : product.getStyles()){
                    listStyle.add(style.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> getbysizeid(int sizeid){

        Size size = sizeRepository.findByid(sizeid);
        if( productRepository.findBysizes(size) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.findBysizes(size)){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size sizesub : product.getSizes()){
                    listSize.add(sizesub.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style style : product.getStyles()){
                    listStyle.add(style.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> getbystyleid(int styleid){

        Style style = styleRepository.findByid(styleid);
        if( productRepository.findBystyles(style) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.findBystyles(style)){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size sizesub : product.getSizes()){
                    listSize.add(sizesub.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style stylesub : product.getStyles()){
                    listStyle.add(stylesub.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> fillterprice(int lowprice,int highprice){

        if( productRepository.fillterByPrice(lowprice,highprice) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.fillterByPrice(lowprice,highprice)){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size sizesub : product.getSizes()){
                    listSize.add(sizesub.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style stylesub : product.getStyles()){
                    listStyle.add(stylesub.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }
                List<RateProduct> rateProducts = rateProductRepository.findByproduct_id(product.getId());

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> sortbyPriceDesc(){

        if( productRepository.sortByPriceDesc() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.sortByPriceDesc()){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size sizesub : product.getSizes()){
                    listSize.add(sizesub.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style stylesub : product.getStyles()){
                    listStyle.add(stylesub.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> sortbyPriceAsc(){

        if( productRepository.sortByPriceAsc() == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            for(Product product : productRepository.sortByPriceAsc()){
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size sizesub : product.getSizes()){
                    listSize.add(sizesub.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style stylesub : product.getStyles()){
                    listStyle.add(stylesub.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);
            }



            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }
    public ResponseEntity<ResponeObject> get(int id){

        if( productRepository.findByid(id) == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false,"Get list product failed ","") );

        }
        else{
            ArrayList<ProductRespone> productRespones = new ArrayList<>();
            Product product = productRepository.findByid(id);
                ProductRespone productRespone = new ProductRespone();

                ArrayList<String> listSize = new ArrayList<>();
                ArrayList<String> listColor= new ArrayList<>();
                ArrayList<String> listStyle= new ArrayList<>();
                ArrayList<String> listCategories= new ArrayList<>();
                ArrayList<String> listReview= new ArrayList<>();
                ArrayList<String> listImage= new ArrayList<>();
                double rate;

                for(Size sizesub : product.getSizes()){
                    listSize.add(sizesub.getSize());
                }
                for(Color colorsub : product.getColors()){
                    listColor.add(colorsub.getColor());
                }
                for(Style stylesub : product.getStyles()){
                    listStyle.add(stylesub.getStyle());
                }
                for(Categories category : product.getCategories()){
                    listCategories.add(category.getName());
                }
                List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(product.getId());
                for (ReviewProduct reviewProduct : reviewProducts) {
                    listReview.add(reviewProduct.getContent());
                }
                List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(product.getId());
                for (ImageProduct imageProduct : imageProducts) {
                    listImage.add(imageProduct.getImage());
                }

                try {
                    rate = productRepository.getAverageRating(product.getId());
                }
                catch (Exception e){
                    rate = 0;
                }

                productRespone.setProductApi(product.getId(),product.getName(),product.getDescription(),product.getPrice(),product.getDiscountprice(),product.getExpired(),product.getView(),rate);
                productRespone.setSize(listSize);
                productRespone.setColor(listColor);
                productRespone.setStyle(listStyle);
                productRespone.setCategories(listCategories);
                productRespone.setListImage(listImage);
                productRespones.add(productRespone);




            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true,"Get list color successfully ",productRespones) );
        }
    }


    public ResponseEntity<ResponeObject> insert(ProductRequest request) {
        Product product = productRepository.findByName(request.getName());

        if (product == null) {

            Product newProduct = new Product(request.getName(), request.getDescription(), request.getPrice(), request.getDiscountprice(), request.getExpired(), request.getView());

            ArrayList<Integer> listSize = request.getSizeid();
            for (Integer sizeitem : listSize) {
                Size size = sizeRepository.findById(sizeitem).orElse(null);
                if (size != null) {
                    newProduct.getSizes().add(size);
                    size.getProducts().add(newProduct);
                }
            }
            ArrayList<Integer> listStyle = request.getStyleid();
            for (Integer styleitem : listStyle) {
                Style style = styleRepository.findById(styleitem).orElse(null);
                if (style != null) {
                    newProduct.getStyles().add(style);
                    style.getProducts().add(newProduct);
                }
            }
            ArrayList<Integer> listColor = request.getColorid();
            for (Integer coloritem : listColor) {
                Color color = colorRepository.findById(coloritem).orElse(null);

                if (color != null) {
                    newProduct.getColors().add(color);
                    color.getProducts().add(newProduct);
                }
            }
            ArrayList<Integer> listCate= request.getCategoryid();
            for (Integer cateitem : listCate) {
                Categories categories = categoriesRepository.findById(cateitem).orElse(null);
                if (categories != null) {

                    newProduct.getCategories().add(categories);
                    categories.getProducts().add(newProduct);
                }
            }

            productRepository.save(newProduct);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Insert product successfully", request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "Insert product failed", "")
            );
        }
    }
    public ResponseEntity<ResponeObject> update(ProducUpdateRequest request) {
        Product product = productRepository.findByid(request.getId());

        if (product != null) {
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setDiscountprice(request.getDiscountprice());
            product.setExpired(request.getExpired());
            product.setView(request.getView());

            ArrayList<Size> listSize = new ArrayList<>();
            for (Integer sizeitem : request.getSizeid()) {
                Size size = sizeRepository.findById(sizeitem).orElse(null);
                if (size != null) {
                    listSize.add(size);
                    List<Product> products = size.getProducts();
                    if (products == null) {
                        products = new ArrayList<>();
                    }
                    if (!products.contains(product)) {
                        products.add(product);
                    }
                    size.setProducts(products);
                    sizeRepository.save(size);
                }
            }
            product.setSizes(listSize);

            ArrayList<Style> listStyle = new ArrayList<>();
            for (Integer styleitem : request.getStyleid()) {
                Style style = styleRepository.findById(styleitem).orElse(null);
                if (style != null) {
                    listStyle.add(style);
                    List<Product> products = style.getProducts();
                    if (products == null) {
                        products = new ArrayList<>();
                    }
                    if (!products.contains(product)) {
                        products.add(product);
                    }
                    style.setProducts(products);
                    styleRepository.save(style);
                }
            }
            product.setStyles(listStyle);


            ArrayList<Color> listColor = new ArrayList<>();
            for (Integer coloritem : request.getColorid()) {
                Color color = colorRepository.findById(coloritem).orElse(null);
                if (color != null) {
                    listColor.add(color);
                    List<Product> products = color.getProducts();
                    if (products == null) {
                        products = new ArrayList<>();
                    }
                    if (!products.contains(product)) {
                        products.add(product);
                    }
                    color.setProducts(products);
                    colorRepository.save(color);
                }
            }
            product.setColors(listColor);

            ArrayList<Categories> listcategory = new ArrayList<>();
            for (Integer categoryitem : request.getCategoryid()) {
                Categories categories = categoriesRepository.findById(categoryitem).orElse(null);
                if (categories != null) {
                    listcategory.add(categories);
                    List<Product> products = categories.getProducts();
                    if (products == null) {
                        products = new ArrayList<>();
                    }
                    if (!products.contains(product)) {
                        products.add(product);
                    }
                    categories.setProducts(products);
                    categoriesRepository.save(categories);
                }
            }
            product.setCategories(listcategory);

            productRepository.save(product);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Update product successfully", request)
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This product does not exist", "")
            );
        }
    }
    public ResponseEntity<ResponeObject> delete(int id) {
        Product product = productRepository.findByid(id);
        if (product != null) {

            List<Color> colors = product.getColors();
            for (Color color : colors){
                color.getProducts().remove(product);
            }

            List<Size> sizes = product.getSizes();
            for (Size size : sizes){
                size.getProducts().remove(product);
            }

            List<Style> styles = product.getStyles();
            for (Style style : styles){
                style.getProducts().remove(product);
            }

            List<Categories> categories = product.getCategories();
            for (Categories category : categories){
                category.getProducts().remove(product);
            }
            List<ImageProduct> imageProducts = imageProductRepository.findByproduct_id(id);
            imageProductRepository.deleteAll(imageProducts);
            List<ReviewProduct> reviewProducts = reviewProductRepository.findByproduct_id(id);
            reviewProductRepository.deleteAll(reviewProducts);
            List<RateProduct> rateProducts = rateProductRepository.findByproduct_id(id);
            rateProductRepository.deleteAll(rateProducts);

            productRepository.delete(product);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponeObject(true, "Delete product successfully", "")
            );

        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponeObject(false, "This product does not exist", "")
            );
        }
    }

}
