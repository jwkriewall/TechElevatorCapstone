<template>
   <div class="well">
        <span class="amount" v-on:click="updateFilter">{{ averageRating }}</span>
        Average Rating
    </div>
</template>

<script>
export default {
    name: 'average-summary',
    methods: {
        updateFilter() {
            this.$store.commit("UPDATE_FILTER", 0);
        }
    },
    computed: {
        averageRating() {
            const reviews = this.$store.state.products.find(
                product => product.id == this.$store.state.activeProduct
            ).reviews;
            let sum = reviews.reduce( (currentSum, review) => {
                return currentSum + review.rating;
            }, 0);
            return (sum / reviews.length).toFixed(2);
        },
    }
}
</script>

<style>

</style>