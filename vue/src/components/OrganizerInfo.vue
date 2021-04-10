<template>
    <div class="organizer-info">
        <h1>Organizer Information</h1>
        <table>
            <tr>
                <td class="label">Name: </td><td>{{ organizer.firstName}} {{organizer.lastName}}</td>
            </tr>
            <tr>
                <td class="label">Email: </td><td>{{ organizer.email}}</td>
            </tr>
            <tr>
                <td class="label">Phone: </td><td>{{organizer.phone}}</td>
            </tr>
        </table>
    </div>
</template>

<script>
export default {
    name: 'user-info',
    props: ['organizer'],
}
</script>

<style>
</style>