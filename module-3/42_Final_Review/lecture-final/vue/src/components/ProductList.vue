<template>
  <div class="products">
      <product-list-item 
        v-for="product in products"
        v-bind:key="product.id"
        v-bind:product="product" />
  </div>

</template>

<script>
import ProductListItem from './ProductListItem.vue';
import productService from '@/services/ProductService';

export default {
    name: 'product-list',
    components: {
        ProductListItem
    },
    computed: {
        products() {
            const prodList = this.$store.state.products;
            const category = this.$store.state.category;
            return prodList.filter( product => {
                return category === 'All'? true : product.category === category;
            })
        }
    },
    created() {
        productService.getAllProducts()
            .then( response => {
                this.$store.commit("SET_PRODUCTS", response.data);
            })
            .catch( error => {
                console.error( error );
            });
    }
    
}
</script>

<style>
div.products {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    width: 90%;
}
</style>