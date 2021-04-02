import axios from 'axios';

export default {

    getAllProducts() {
        return axios.get("/products");
    }, 
    makePurchase(products) {
        return axios.post("/purchases", products);
    }
}