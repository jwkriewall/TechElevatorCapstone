<template>
  <div id="cart">
      <h1>Contents of you cart</h1>
      <p>Your total: ${{ cartTotal }}</p>
      <div>
          <button v-on:click.prevent="makePurchase">Purchase</button>
      </div>
      <product-list-item v-for="product in $store.state.cart" v-bind:key="product.id" 
        v-bind:product="product" />
  </div>
</template>

<script>
import ProductListItem from '@/components/ProductListItem';
import productService from '@/services/ProductService';

export default {
    components: {
        ProductListItem
    },
    computed: {
        cartTotal() {
            return this.$store.state.cart.reduce( (total, product) => {
                return total += product.price;
            }, 0).toFixed(2);
        }
    },
    methods: {
        makePurchase() {
            productService.makePurchase(this.$store.state.cart)
                .then( response => {
                    if (response.status === 200) {
                        alert("Your purchase was successful.  Confirmation number: " + response.data.purchaseId);
                        this.$store.commit("CLEAR_CART");
                        this.$router.push( { name: 'home'} );
                    }
                })
                .catch( err => { console.error(err) } );
        }
    }

}
</script>

<style>

</style>