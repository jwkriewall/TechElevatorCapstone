<template>
  <h1> counter: {{ $store.state.bracketPosition }}</h1>
</template>

<script>
export default {
    name: 'draggable-element',
    created() {
        this.update();
    },
    data() {
        return {
            number: 0,
        }
    },
    methods: {
        update() {
            this.$store.commit('UPDATE_BRACKET_POSITION');
        }
    }
}
</script>

<style>

</style>