<template>
    <div class ="main">
        <h2> Product Reviews for {{ name }} </h2>
        <p class="description">{{ description }}</p>

        <select v-model="showDescription">
            <option v-bind:value="true">Show Description</option>
            <option v-bind:value="false">Hide Description</option>
        </select>

        <div class ="well-display">
            <div class="well">
                <span class="amount">{{averageRating}}</span>
                Average rating
            </div>

            <div class="well">
                <span class="amount">{{ numberOfOneStarReviews}}</span>
                1 Star Review{{numberOfOneStarReviews === 1 ? '' : 's'}}
            </div>
            <div class="well">
                <span class="amount">{{ numberOfTwoStarReviews}} </span>
                2 Star Review{{numberOfTwoStarReviews === 1 ? '' : 's'}}
            </div>
            <div class="well">
                <span class="amount">{{ numberOfThreeStarReviews}}</span>
                3 Star Review{{numberOfThreeStarReviews === 1 ? '' : 's'}}
            </div>
            <div class="well">
                <span class="amount">{{ numberOfFourStarReviews}}</span>
                4 Star Review{{numberOfFourStarReviews === 1 ? '' : 's'}}
            </div>
        </div>   

        <div class="review" v-for="review in reviews" v-bind:key="review.id">
            <h4> {{ review.reviewer }}</h4>
            <div class="rating">
                <img src="../assets/star.png" v-for="i in reviews.rating"
                v-bind:key="i" v-bind:title="review.rating + ' Star Review'"/>
            </div>

            
        <h3>{{ review.title }}</h3>
        <p>{{ review.review }}</p>
        </div>
    </div>

   
</template>

<script>
// defines an object -->
export default {
    name: 'product-reviews',
    // data() builds a method that returns an object
    data() {
        return {
            name: 'Cigar Parties for Dummies',
            description: '????This can be anything!',
            showDescription: true,
            reviews: [
                {
                    reviewer: 'Malcolm Gladwell',
                    title: 'What a book!',
                    review:
                    "It certainly is a book. I mean, I can see that. Pages kept together with glue (I hope that's glue) and there's writing on it, in some language.",
                    rating: 3
                },
                {
                    reviewer: 'Tim Ferriss',
                    title: 'Had a cigar party started in less than 4 hours.',
                    review:
                    "It should have been called the four hour cigar party. That's amazing. I have a new idea for muse because of this.",
                    rating: 4
                },
                {
                    reviewer: 'Ramit Sethi',
                    title: 'What every new entrepreneurs needs. A door stop.',
                    review:
                    "When I sell my courses, I'm always telling people that if a book costs less than $20, they should just buy it. If they only learn one thing from it, it was worth it. Wish I learned something from this book.",
                    rating: 1
                },
                {
                    reviewer: 'Gary Vaynerchuk',
                    title: 'And I thought I could write',
                    review:
                    "There are a lot of good, solid tips in this book. I don't want to ruin it, but prelighting all the cigars is worth the price of admission alone.",
                    rating: 3
                }
                ]
        }
    },
    computed: {
        averageRating(){
            let sum = this.reviews.reduce((currentSum, review) => {
                return currentSum + review.rating;
            }, 0);
            return sum/this.reviews.length;
        },
        numberOfOneStarReviews() {
        return this.reviews.reduce((currentCount, review ) =>{
            return currentCount + (review.rating === 1 ? 1:0);
        })        
        
        }
    }
}
</script>

<style scoped>
    div.main {
        margin: 1rem 0;
    }

    div.main div.well-display {
    display: flex;
    justify-content: space-around;
}
div.main div.well-display div.well {
    display: inline-block;
    width: 15%;
    border: 1px black solid;
    border-radius: 6px;
    text-align: center;
    margin: 0.25rem;
}
div.main div.well-display div.well span.amount {
    color: darkslategray;
    display: block;
    font-size: 2.5rem;
}
div.main div.review {
    border: 1px black solid;
    border-radius: 6px;
    padding: 1rem;
    margin: 10px;
}
div.main div.review div.rating {
    height: 2rem;
    display: inline-block;
    vertical-align: top;
    margin: 0 0.5rem;
}
div.main div.review div.rating img {
    height: 100%;
}
div.main div.review p {
    margin: 20px;
}
div.main div.review h3 {
    display: inline-block;
}
div.main div.review h4 {
    font-size: 1rem;
}
</style>