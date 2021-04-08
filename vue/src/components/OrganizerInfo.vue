<template>
    <div class="organizer-info">
        <h1>Organizer Information</h1>
        <ul>
            <li>Name: {{ organizer.firstName}} {{organizer.lastName}}</li>
            <li>Email: {{ organizer.email}}</li>
            <li>Phone: {{organizer.phone}}</li>
        </ul>
    </div>
</template>

<script>
export default {
    name: 'user-info',
    props: ['organizer'],
}
</script>

<style>
ul {
    list-style-position: outside;
    list-style-type: none;
    padding: 0;
}

ul li {
    line-height: 2rem;
    padding:0 10px;
    border-radius:10px;
}

ul li:nth-child(odd) {
    background-color: #f4f4f4;
    color: black;
}
</style>