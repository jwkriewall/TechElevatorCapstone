package com.techelevator;

public class Exercises {

	public static void main(String[] args) {

        /*
        1. 4 birds are sitting on a branch. 1 flies away. How many birds are left on
        the branch?
        */

		// ### EXAMPLE:
		int initialNumberOfBirds = 4;
		int birdsThatFlewAway = 1;
		int remainingNumberOfBirds = initialNumberOfBirds - birdsThatFlewAway;

        /*
        2. There are 6 birds and 3 nests. How many more birds are there than
        nests?
        */

		// ### EXAMPLE:
		int numberOfBirds = 6;
		int numberOfNests = 3;
		int numberOfExtraBirds = numberOfBirds - numberOfNests;
		System.out.println(numberOfExtraBirds);

        /*
        3. 3 raccoons are playing in the woods. 2 go home to eat dinner. How
        many raccoons are left in the woods?
        */
		int racoonsPlaying = 3;
		int racoonsThatLeave = 2;
		int racoonsLeft = racoonsPlaying - racoonsThatLeave;
        /*
        4. There are 5 flowers and 3 bees. How many less bees than flowers?
        */
		int numberOfFlowers = 5;
		int numberOfBees = 3;
		int differenceOfFlowersAndBees = numberOfFlowers - numberOfBees;

        /*
        5. 1 lonely pigeon was eating breadcrumbs. Another pigeon came to eat
        breadcrumbs, too. How many pigeons are eating breadcrumbs now?
        */
		int lonelyPigeonEatingBreadcrumbs = 1;
		int hungryPigeon = 1;
		int totalPigeonsEatingBreadcrumbs = lonelyPigeonEatingBreadcrumbs + hungryPigeon;

        /*
        6. 3 owls were sitting on the fence. 2 more owls joined them. How many
        owls are on the fence now?
        */
		int owlsOnFence = 3;
		int owlsJoining = 2;
		int totalOwlsOnFence = owlsOnFence + owlsJoining;

        /*
        7. 2 beavers were working on their home. 1 went for a swim. How many
        beavers are still working on their home?
        */
		int beaversWorkingOnHome = 2;
		int swimmingBeavers = 1;
		int beaversWhoRemainWorkingOnHome = beaversWorkingOnHome - swimmingBeavers;

        /*
        8. 2 toucans are sitting on a tree limb. 1 more toucan joins them. How
        many toucans in all?
        */
		int toucansOnATreeLimb = 2;
		int toucansWhoAreJoining = 1;
		int totalToucans = toucansOnATreeLimb + toucansWhoAreJoining;
        /*
        9. There are 4 squirrels in a tree with 2 nuts. How many more squirrels
        are there than nuts?
        */
		int squirrelsInTheTree = 4;
		int nutsInTree = 2;
		int differenceBetweenSquirrelsAndNuts = squirrelsInTheTree - nutsInTree;
        /*
        10. Mrs. Hilt found a quarter, 1 dime, and 2 nickels. How much money did
        she find?
        */
		int quarter = 25;
		int dime = 10;
		int nickel = 5;
		int mrsHiltTotalCents = quarter + dime + (2 * nickel);
        /*
        11. Mrs. Hilt's favorite first grade classes are baking muffins. Mrs. Brier's
        class bakes 18 muffins, Mrs. MacAdams's class bakes 20 muffins, and
        Mrs. Flannery's class bakes 17 muffins. How many muffins does first
        grade bake in all?
        */
		int hiltClassMuffins = 18;
		int macAdamsClassMuffins = 20;
		int flanneryClassMuffins = 17;
		int firstGradeMuffinsBaked = hiltClassMuffins + macAdamsClassMuffins + flanneryClassMuffins;
        /*
        12. Mrs. Hilt bought a yoyo for 24 cents and a whistle for 14 cents. How
        much did she spend in all for the two toys?
        */
		int yoyoCostCents = 24;
		int whistleCostCents = 14;
		int totalToyCost = yoyoCostCents + whistleCostCents;
        /*
        13. Mrs. Hilt made 5 Rice Krispie Treats. She used 8 large marshmallows
        and 10 mini marshmallows. How many marshmallows did she use
        altogether?
        */
		int totalRiceKrispies = 5;
		int largeMarshmallowCount = 8;
		int smallMarshmallowCount = 10;
		int totalMarshmallowsUsed = totalRiceKrispies*(largeMarshmallowCount + smallMarshmallowCount);
        /*
        14. At Mrs. Hilt's house, there was 29 inches of snow, and Brecknock
        Elementary School received 17 inches of snow. How much more snow
        did Mrs. Hilt's house have?
        */
		int hiltHouseSnowInInches = 29;
		int brecknockSchoolSnowInInches = 17;
		int differenceInSnowInInches = hiltHouseSnowInInches - brecknockSchoolSnowInInches;
        /*
        15. Mrs. Hilt has $10. She spends $3 on a toy truck and $2 on a pencil
        case. How much money does she have left?
        */
		int hiltTotalMoneyInDollars = 10;
		int costOfTruckInDollars = 3;
		int costOfPencilCaseInDollars = 2;
		int hiltRemainingMoney = hiltTotalMoneyInDollars - (costOfTruckInDollars + costOfPencilCaseInDollars);
        /*
        16. Josh had 16 marbles in his collection. He lost 7 marbles. How many
        marbles does he have now?
        */
		int joshTotalMarbles = 16;
		int lostMarbles = 7;
		int joshRemainingMarbles = joshTotalMarbles - lostMarbles;
        /*
        17. Megan has 19 seashells. How many more seashells does she need to
        find to have 25 seashells in her collection?
        */
		int megansSeashells = 19;
		int meganDesiredNumberOfSeashells = 25;
		int meganCollectionGoals = meganDesiredNumberOfSeashells - megansSeashells;
        /*
        18. Brad has 17 balloons. 8 balloons are red and the rest are green. How
        many green balloons does Brad have?
        */
		int bradsBalloons = 17;
		int redBalloons = 8;
		int greenBalloons = bradsBalloons - redBalloons;
        /*
        19. There are 38 books on the shelf. Marta put 10 more books on the shelf.
        How many books are on the shelf now?
        */
		int booksOnTheShelf = 38;
		int martasBooks = 10;
		int totalBooks = booksOnTheShelf + martasBooks;
        /*
        20. A bee has 6 legs. How many legs do 8 bees have?
        */
		int beeLegCount = 6;
		int bees = 8;
		int totalBeeLegCount = beeLegCount * bees;
        /*
        21. Mrs. Hilt bought an ice cream cone for 99 cents. How much would 2 ice
        cream cones cost?
        */
		int iceCreamConeInCents = 99;
		int numberOfCones = 2;
		int totalIceCreamCost = iceCreamConeInCents * numberOfCones;
        /*
        22. Mrs. Hilt wants to make a border around her garden. She needs 125
        rocks to complete the border. She has 64 rocks. How many more rocks
        does she need to complete the border?
        */
		int borderRockTotal = 125;
		int hiltCurrentBorderRocks = 64;
		int differenceInRocks = borderRockTotal - hiltCurrentBorderRocks;
        /*
        23. Mrs. Hilt had 38 marbles. She lost 15 of them. How many marbles does
        she have left?
        */
		int hiltTotalMarbles = 38;
		int hiltLostMarbles = 15;
		int totalMarblesRemaining = hiltTotalMarbles - hiltLostMarbles;
        /*
        24. Mrs. Hilt and her sister drove to a concert 78 miles away. They drove 32
        miles and then stopped for gas. How many miles did they have left to drive?
        */
		int concertDistanceInMiles = 78;
		int firstLegOfTripInMiles = 32;
		int remainingMileageToConcert = concertDistanceInMiles - firstLegOfTripInMiles;
        /*
        25. Mrs. Hilt spent 1 hour and 30 minutes shoveling snow on Saturday
        morning and 45 minutes shoveling snow on Saturday afternoon. How
        much total time did she spend shoveling snow?
        */
		int saturdayMorningSnowShovelingTimeSpentMinutes = 90;
		int saturdayAfternoonSnowShovelingTimeSpentMinutes = 45;
		int totalTimeSpentShovelingSnow = saturdayMorningSnowShovelingTimeSpentMinutes + saturdayAfternoonSnowShovelingTimeSpentMinutes;
        /*
        26. Mrs. Hilt bought 6 hot dogs. Each hot dog cost 50 cents. How much
        money did she pay for all of the hot dogs?
        */
		int hiltHotdogPurchase = 6;
		int hotdogCostCents = 50;
		int totalSpentOnHotdogsCents = hiltHotdogPurchase * hotdogCostCents;
        /*
        27. Mrs. Hilt has 50 cents. A pencil costs 7 cents. How many pencils can
        she buy with the money she has?
        */
		int hiltTotalMoneyInCents = 50;
		int pencilCostInCents = 7;
		int totalPencilsAbleToPurchase = hiltTotalMoneyInCents / pencilCostInCents;
		System.out.println(totalPencilsAbleToPurchase);
        /*
        28. Mrs. Hilt saw 33 butterflies. Some of the butterflies were red and others
        were orange. If 20 of the butterflies were orange, how many of them
        were red?
        */
		int hiltSeenButterflies = 33;
		int orangeButterflies = 20;
		int redButterflies = hiltSeenButterflies - orangeButterflies;
        /*
        29. Kate gave the clerk $1.00. Her candy cost 54 cents. How much change
        should Kate get back?
        */
		int kateMoneyInCents = 100;
		int candyCost = 54;
		int kateChangeReceivedInCents = kateMoneyInCents - candyCost;
        /*
        30. Mark has 13 trees in his backyard. If he plants 12 more, how many trees
        will he have?
        */
		int markTrees = 13;
		int treesPlannedToPlant = 12;
		int futureTotalTrees = markTrees + treesPlannedToPlant;
        /*
        31. Joy will see her grandma in two days. How many hours until she sees
        her?
        */
		int daysTilJoySeesGrandma = 2;
		int hoursInDay = 24;
		int remainingHoursTilJoySeesGrandma = daysTilJoySeesGrandma * hoursInDay;
        /*
        32. Kim has 4 cousins. She wants to give each one 5 pieces of gum. How
        much gum will she need?
        */
		int kimCousins = 4;
		int kimsGumGift = 5;
		int totalGumNeeded = kimCousins * kimsGumGift;
        /*
        33. Dan has $3.00. He bought a candy bar for $1.00. How much money is
        left?
        */
		int danMoneyTotalInCents = 300;
		int candyBarCostCents = 100;
		int moneyRemainingAfterCandyPurchase = danMoneyTotalInCents - candyBarCostCents;
        /*
        34. 5 boats are in the lake. Each boat has 3 people. How many people are
        on boats in the lake?
        */
		int boatsOnLake = 5;
		int peoplePerBoat = 3;
		int peopleOnLake = boatsOnLake*peoplePerBoat;
        /*
        35. Ellen had 380 legos, but she lost 57 of them. How many legos does she
        have now?
        */
		int ellenTotalLegos = 300;
		int lostLegos = 57;
		int ellenRemainingLegos = ellenTotalLegos - lostLegos;
        /*
        36. Arthur baked 35 muffins. How many more muffins does Arthur have to
        bake to have 83 muffins?
        */
		int arthursBakedMuffins = 35;
		int idealTotalOfMuffins = 83;
		int muffinsLeftToBake = idealTotalOfMuffins - arthursBakedMuffins;
        /*
        37. Willy has 1400 crayons. Lucy has 290 crayons. How many more
        crayons does Willy have then Lucy?
        */
		int willyCrayonTotal = 1400;
		int lucyCrayonTotal = 290;
		int differenceInCrayons = willyCrayonTotal - lucyCrayonTotal;
        /*
        38. There are 10 stickers on a page. If you have 22 pages of stickers, how
        many stickers do you have?
        */
		int stickersOnPage = 10;
		int totalPagesOfStickers = 22;
		int totalStickers = stickersOnPage * totalPagesOfStickers;
        /*
        39. There are 96 cupcakes for 8 children to share. How much will each
        person get if they share the cupcakes equally?
        */
		double totalCupcakes = 96.0;
		double numberOfChildren = 8.0;
		double cupcakesPerPerson = totalCupcakes / numberOfChildren;
		System.out.println(cupcakesPerPerson);
        /*
        40. She made 47 gingerbread cookies which she will distribute equally in
        tiny glass jars. If each jar is to contain six cookies each, how many
        cookies will not be placed in a jar?
        */
		int gingerbreadCookiesTotal = 47;
		int numberOfCookiesPerJar = 6;
		int cookiesRemainingNotJarred = gingerbreadCookiesTotal % numberOfCookiesPerJar;
        /*
        41. She also prepared 59 croissants which she plans to give to her 8
        neighbors. If each neighbor received and equal number of croissants,
        how many will be left with Marian?
        */
		int totalCroissants = 59;
		int neighbors = 8;
		int undistributedCroissants = totalCroissants % neighbors;
        /*
        42. Marian also baked oatmeal cookies for her classmates. If she can
        place 12 cookies on a tray at a time, how many trays will she need to
        prepare 276 oatmeal cookies at a time?
        */
		int cookiesPerTray = 12;
		int desiredNumberOfCookies = 276;
		int totalTraysNeeded = desiredNumberOfCookies / cookiesPerTray;
        /*
        43. Marian’s friends were coming over that afternoon so she made 480
        bite-sized pretzels. If one serving is equal to 12 pretzels, how many
        servings of bite-sized pretzels was Marian able to prepare?
        */
		int totalPretzels = 480;
		int servingSizePretzels = 12;
		int totalServingsPretzels = totalPretzels / servingSizePretzels;
        /*
        44. Lastly, she baked 53 lemon cupcakes for the children living in the city
        orphanage. If two lemon cupcakes were left at home, how many
        boxes with 3 lemon cupcakes each were given away?
        */
		int bakedLemonCupcakes = 53;
		int forgottenCupcakes = 2;
		int cupcakesPerBox = 3;
		int boxesOfCupcakes = (bakedLemonCupcakes - forgottenCupcakes) % cupcakesPerBox;
        /*
        45. Susie's mom prepared 74 carrot sticks for breakfast. If the carrots
        were served equally to 12 people, how many carrot sticks were left
        uneaten?
        */
		int totalCarrotSticks = 74;
		int numberOfPeople = 12;
		int uneatenCarrotSticks = totalCarrotSticks % numberOfPeople;
        /*
        46. Susie and her sister gathered all 98 of their teddy bears and placed
        them on the shelves in their bedroom. If every shelf can carry a
        maximum of 7 teddy bears, how many shelves will be filled?
        */
		int totalTeddyBears = 98;
		int numberOfTeddyBearsPerShelf = 7;
		int totalShelvesFilled = totalTeddyBears % numberOfTeddyBearsPerShelf;
        /*
        47. Susie’s mother collected all family pictures and wanted to place all of
        them in an album. If an album can contain 20 pictures, how many
        albums will she need if there are 480 pictures?
        */
		int totalPictures = 480;
		int maxPicsPerAlbum = 20;
		int totalAlbumsNeeded = totalPictures / maxPicsPerAlbum;
        /*
        48. Joe, Susie’s brother, collected all 94 trading cards scattered in his
        room and placed them in boxes. If a full box can hold a maximum of 8
        cards, how many boxes were filled and how many cards are there in
        the unfilled box?
        */
		int joesTradingCards = 94;
		int maxCardsInFullBox = 8;
		int numberOfBoxesFilled = joesTradingCards / maxCardsInFullBox;
		int unfilledBoxOfCards = joesTradingCards % maxCardsInFullBox;
        /*
        49. Susie’s father repaired the bookshelves in the reading room. If he has
        210 books to be distributed equally on the 10 shelves he repaired,
        how many books will each shelf contain?
        */
		int dadsBooks = 210;
		int numberOfShelves = 10;
		int numberOfShelvesNeededToStoreAllBooks = dadsBooks / numberOfShelves;
        /*
        50. Cristina baked 17 croissants. If she planned to serve this equally to
        her seven guests, how many will each have?
        */
		int totalNumberOfCroissants = 17;
		int numberOfGuests = 7;
		float croissantsPerGuest = totalNumberOfCroissants / numberOfGuests;
        /*
            CHALLENGE PROBLEMS
        */

        /*
        Bill and Jill are house painters. Bill can paint a 12 x 14 room in 2.15 hours, while Jill averages
        1.90 hours. How long will it take the two painter working together to paint 5 12 x 14 rooms?
        Hint: Calculate the hourly rate for each painter, combine them, and then divide the total walls in feet by the combined hourly rate of the painters.
        Challenge: How many days will it take the pair to paint 623 rooms assuming they work 8 hours a day?.
        */
		int surfaceAreaToBePainted = (12 * 2) + (14 * 2);
		double numberOfRoomsToBePainted = 5;
		int largeRoomPaintingProject = 623;
		int workHours = 8;
		double billPaintTime = 2.15;
		double jillPaintTime = 1.90;
		double combinedBillJillTimeToPaintOneRoom = surfaceAreaToBePainted / (billPaintTime + jillPaintTime);
		double timeToPaintRooms = numberOfRoomsToBePainted * (combinedBillJillTimeToPaintOneRoom);
		//Challenge answer below
		double timeNeededToPaintManyRooms = largeRoomPaintingProject *(combinedBillJillTimeToPaintOneRoom) / workHours;
        /*
        Create and assign variables to hold your first name, last name, and middle initial. Using concatenation,
        build an additional variable to hold your full name in the order of last name, first name, middle initial. The
        last and first names should be separated by a comma followed by a space, and the middle initial must end
        with a period.
        Example: "Hopper, Grace B."
        */
		String lastName = "Kriewall";
		String middleInitial = "W";
		String firstName = "JW";
		String fullName = lastName + ", " + firstName + " " + middleInitial + ".";
        /*
        The distance between New York and Chicago is 800 miles, and the train has already travelled 537 miles.
        What percentage of the trip has been completed?
        Hint: The percent completed is the miles already travelled divided by the total miles.
        Challenge: Display as an integer value between 0 and 100 using casts.
        */
		int distanceBetweenNewYorkAndChicagoInMiles = 800;
		int distanceTravelled = 537;
		double percentageOfTripCompleted = distanceTravelled / distanceBetweenNewYorkAndChicagoInMiles;


	}

	

}
