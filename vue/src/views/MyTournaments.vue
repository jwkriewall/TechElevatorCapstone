<template>
    <div class="my-tournaments">
        <h1>My Tournaments</h1>
        <display-tournaments />
    </div>
</template>

<script>
import DisplayTournaments from '../components/DisplayTournaments.vue';

export default {
    name: 'my-tournaments',
    components: { DisplayTournaments },
}
</script>

<style>

</style>