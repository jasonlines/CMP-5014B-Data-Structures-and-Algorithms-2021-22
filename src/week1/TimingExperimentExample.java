package week1;

import java.util.Random;

public class TimingExperimentExample {

    // A simple method to sum up all elements within an array.
    public static int sumArray(int[] input){
        int sum = 0;
        int countOps = 0;
        for(int i = 0; i < input.length; i++){
            sum += input[i];
        }
        return sum;
    }

    // A copy of sumArray BUT with the addition that it tracks the number of fundamental operations and prints them.
    // Remember - when analysing and algorithm, the first step is to identify the fundamental operation(s) - this is the
    // work that is carried out at the heart of the algorithm and really defines what it does. In this case, our
    // algorithm is summing and array, so the operatoin at the heart of the algorithm is where each element from the
    // input is observed and the running sum is updated (e.g. sum += input[i]). A key observation is that this operation
    // happens once for each element within the inpt - so if you double the size of the input, you would expect to do
    // double the number of fundamental operations (which means this would be a linear time, or O(n), algorithm)
    public static int sumArrayPrintingOps(int[] input){
        int sum = 0;
        int countOps = 0;
        for(int i = 0; i < input.length; i++){
            sum += input[i];
            countOps++;
        }
        System.out.print(countOps+",");
        return sum;
    }

    // a helper method to generate random int arrays of a given size. The output is unseeded (i.e. will change each
    // time you run the code) and will contain only ints in the range of 0-9 inclusive. If you'd like to change this,
    // pass an argument to the Random constructor (which we call a seed) and if you use the same seed twice for two
    // arrays of the same size then your arrays should be identical (you could pass this into the method as an arg,
    // perhaps). This isn't necessary here but it's good to be aware of. Further, the nextInt() method of Random
    // will give you the next int in the random sequence bounded above by the value that is passed in as an arg. If you
    // want to be able to include values greater than 9 (no reason to here but just an FYI) then change the arg that is
    // passed to that method to a larger number - it will return a random int between 0 (inclusive) and whatever that
    // argument is (exclusive). E.g. if you pass in 5, it could return 0, 1, 2, 3, or 4 (but not 5).
    public static int[] generateArray(int size){
        int[] output = new int[size];
        Random r = new Random();
        for(int i = 0; i < size; i++){
            output[i] = r.nextInt(10);
        }
        return output;
    }

    public static void main(String[] args) {

        // Here we will run some practical timing experiments. The objective here is to start with a very simple (but
        // crude) experiment and incrementally improve upon our procedure. As discussed in the lecture, it's best to
        // compare algorithms by analysing and characterising their run-time complexities (e.g. liner/O(n),
        // logarithmic/O(logn), quadratic/O(n^2), etc.) as this reduces any confounding variables such as computing
        // hardware, other background applications, slower memory, etc.
        //
        // However, it can also be useful to compare two implemented algorithms in terms of their real-world runtimes
        // by carrying out a timing experiment (some times these are called wall-clock experiments as we are recording
        // the time they take). Experiments like this can be particularly useful in testing and debugging, as well as
        // purley for comparison, because you can see if run-times are scaling as expected in practice (i.e. if you
        // implement an algorithm that is supposed to be linear in the worst case, but your experiments appear to show
        // that you have a quadratic-time algorithm, then it's possible there is a bug in your code/an external library
        // as the code is taking longer than expected).
        //
        // The best way to look at the timing results (in my opinion!) is to print them in code/write them to file and
        // then plot some graphs in Excel (please see the recoded live lecture for how I did this - I'll also include
        // my Excel file in the upload along with this code). For simplicity, here we will just print the results to
        // the console and then paste them into Excel (but you can change this to write results directly to file if
        // you would like a bit of file I/O practice!
        //
        // List of experiments:
        //      1. simple timing experiment using one repetition and a "cold start"
        //      2. increasing the size and range of inputs from #1 to plot more data
        //      3. repeating #2, but doing 10 repetitions for each input size to get a more stable reading
        //      4. running experiment #3 twice to avoid the "cold start" problem. This is caused by additional overheads
        //         in the code on the first run (e.g. like an old car enging starting from cold and taking a while to
        //         warm up). By repeating the experiments, the JVM will be running code more consistently for different
        //         inputs
        //      5. The final experiment is using an adapted version of the code to count operations rather than time to
        //         demonstrate why we like to analyse algorithms in this way, rather than relying purely on timing
        //         experiments (as it is a linear algorithm the output will be quite boring on this occasion, but you
        //         can try similar with the srting algorithms in the week 1 lab exercises to see what happens there)


//        System.out.println("Experiment 1:");
//        experiment1();
//        System.out.println("------------\n");

//        System.out.println("Experiment 2:");
//        experiment2();
//        System.out.println("------------\n");

//        System.out.println("Experiment 3:");
//        experiment3();
//        System.out.println("------------\n");

//        System.out.println("Experiment 4:");
//        experiment4();
//        System.out.println("------------\n");

        System.out.println("Experiment 5:");
        experiment5();
        System.out.println("------------\n");

    }

    // very simple experiment (no repetitions, small data size, cold start)
    public static void experiment1() {

        int[] input;
        int sum;
        long start, end, total;

        // arrays of length 100, 200, ..., 900, 1000
        for(int arrayLength = 100; arrayLength <= 1000; arrayLength+=100){

            // the output will be a separate line for each array size - let's start with that and then add times later
            System.out.print(arrayLength+",");

            // remember - we don't want to include the time taken to generate our input in the timing experiment
            // as it's not part of the algorithm itself - hence we generate data and THEN start the timer
            input = generateArray(arrayLength);

            // very simple procedure for timing - take note of the start time, run the method, take note of the end
            // time, and then work out the difference
            start = System.nanoTime();
            sum = sumArray(input);
            end = System.nanoTime();
            total = end - start;

            // now we know what total is, print that onto our current line
            System.out.print(total);

            // and then finish off with a println to move the output onto a new line (we are doing it in a slightly
            // weird way to make the code consistent with the other experiments - it'll make sense when we add
            // more repetitions!
            System.out.println();
        }

        // your output should be something like this:
        //        100,1900
        //        200,1900
        //        ...
        //        900,7900
        //        1000,8700
        //
        // These times are currently TINY (there are 1,000,000,000 nanoseconds in 1 second) so it's hard to read too
        // much into this. Better to recreate this experiment but with bigger values over a larger range

    }

    // similar to #1 but using a better range of values
    public static void experiment2() {

        int[] input;
        int sum;
        long start, end, total;

        // arrays of length 10000, 20000, ..., 900000, 1000000
        for(int arrayLength = 10000; arrayLength <= 1000000; arrayLength+=1000){

            // as before in #1 - please see comments there for more detail
            System.out.print(arrayLength+",");

            input = generateArray(arrayLength);

            start = System.nanoTime();
            sum = sumArray(input);
            end = System.nanoTime();
            total = end - start;

            System.out.print(total);
            System.out.println();
        }

        // your output should be something like this:
        //        10000,86700
        //        11000,119200
        //        12000,102600
        //        ...
        //        999000,261300
        //        1000000,306600
        //
        // This is looking better but there is noise in the readings (e.g. in my results that I've pasted above, why
        // does it take less time for an input of 12000 than it does for 11000? This is because my computer may have
        // been doing other things in the background and this could affect how quickly my CPU can operate. There are
        // also many other things that could confound these results. Two things that we can do to help are:
        //  1) repeat the experiment many times and take averages (i.e. smooth out the noise)
        //  2) avoid the "cold start" problem by running the complete experiment twice and ignoring the first go
        //  3) count fundamental operations rather than elapsed time for the fairest comparison
        // We will now do all of those things!
    }

    // similar to #2 but now we will repeat each experiment 10 times and report the average. In the live lecture I just
    // printed the individual results and averaged in Excel - here we'll print out those individual values still but
    // we will also calculate the average at the same time. As an extra exercise, try looking at the min/max values
    // for a given input size to see how much variance there is in run-time, even when everything should be equal!
    public static void experiment3() {

        int[] input;
        int sum;
        long start, end, total, tenRunTotal;

        // arrays of length 10000, 20000, ..., 900000, 1000000
        for(int arrayLength = 10000; arrayLength <= 1000000; arrayLength+=1000){
            System.out.print(arrayLength + ",");
            // now we add a nested loop to repeat each value 10 times. We also need to keep a total of the time over the
            // 10 runs to average at the end
            tenRunTotal = 0;
            for(int repetition = 0; repetition < 10; repetition++) {
                // as before in #1 - please see comments there for more detail

                input = generateArray(arrayLength);
                start = System.nanoTime();
                sum = sumArray(input);
                end = System.nanoTime();
                total = end - start;
                System.out.print(total+",");
                tenRunTotal += total;
            }
            // to make it clear when copying into Excel we'll add in a blank column before printing the average
            System.out.println(","+(tenRunTotal/10));
        }

        // your output should be something like this:
        //        10000,133200,85600,93800,87600,85700,85600,86100,91600,88800,28300,,86630
        //        11000,26900,32700,27200,30100,30000,27800,26200,16900,17000,16700,,25150
        //        12000,18400,18200,19700,18400,18600,18100,18600,18300,18300,18400,,18500
        //        ...
        //        999000,328500,236100,339900,257200,298100,235400,298000,238500,267300,256400,,275540
        //        1000000,296500,320500,302300,243900,302800,248700,268400,237400,259100,236400,,271600
        //
        // Remember that this includes the average at the end (i.e. after the ,,) whereas the verison I showed live
        // did not include this.
        //
        // This is starting to look better but there are clearly some weird things going on. The first run for an input
        // of 10,000 is 133,200, whereas the 10th run for that same input size is only 28,300 - that means the 10th
        // repetition only took about 21% of the time that the first one did - and that's clearly a bit odd! Similarly,
        // the average for the 3rd input size (12,000) is less than the 2nd input size (11,000). Remember that we are
        // still talking about VERY small units of time, so it's important to not read too much into tiny amounts, but
        // we would still wish to try to smooth out this data a bit more. Our next attempt is to run the experiment
        // twice but only look at the results for the second run (e.g. eliminate the cold start problem and only look at
        // results once the engine/JVM is "warm")
    }

    public static void experiment4() {

        int[] input;
        int sum;
        long start, end, total, tenRunTotal;

        // running our experiment but not prinitng it out - we'll rerun after and print that instead
        System.out.println("Running initial set of experiments... (this will take a little while - please wait!)");
        for(int arrayLength = 10000; arrayLength <= 1000000; arrayLength+=1000){
            tenRunTotal = 0;
            for(int repetition = 0; repetition < 10; repetition++) {
                // as before in #1 - please see comments there for more detail

                input = generateArray(arrayLength);
                start = System.nanoTime();
                sum = sumArray(input);
                end = System.nanoTime();
                total = end - start;
                tenRunTotal += total;
            }
        }
        System.out.println("Initial run completed. Recorded experiments begin:");

        for(int arrayLength = 10000; arrayLength <= 1000000; arrayLength+=1000){
            System.out.print(arrayLength + ",");
            tenRunTotal = 0;
            for(int repetition = 0; repetition < 10; repetition++) {
                input = generateArray(arrayLength);
                start = System.nanoTime();
                sum = sumArray(input);
                end = System.nanoTime();
                total = end - start;
                System.out.print(total+",");
                tenRunTotal += total;
            }
            System.out.println(","+(tenRunTotal/10));
        }

        // From experiment #3, the first 3 lines of the output (when I ran it) were:
        //        10000,133200,85600,93800,87600,85700,85600,86100,91600,88800,28300,,86630
        //        11000,26900,32700,27200,30100,30000,27800,26200,16900,17000,16700,,25150
        //        12000,18400,18200,19700,18400,18600,18100,18600,18300,18300,18400,,18500
        //
        // Now that we're taking care of the cold start problem, my output for experiment #4 is:
        //        10000,2400,2400,2300,2300,2300,2400,2300,2300,2300,2300,,2330
        //        11000,2500,2500,2500,2500,2500,2500,2500,2500,2500,2700,,2520
        //        12000,2700,2700,2700,2700,2700,2600,2700,2700,2700,2700,,2690
        //
        // It's clear that this has helped as the times are not only much smaller, but also more consistent. We can also
        // observe this at the end of the experiments with the larger inputs (I'll leave that as an exercise for you to
        // investigate, however!). If your results look like mine then they are certainly looking better, but they
        // certainly are not perfect - even with the cold start eliminated, and taking averages over 10 repetitions, we
        // still have readings that include noise (e.g. larger inputs occasionally taking less time than smaller ones).
        // As mentioned, this can be for many reasons such as background computation, and we are still dealing with
        // very small units of time, but it's worth noting that we would not expect any noise under perfect conditions
        // in a controlled environment - this is what we hope to achieve by counting fundamental operations rather than
        // explicit timing expeirments. This is what we will show in the final experiment below
    }

    public static void experiment5() {
        // we will noe use the generateArrayPrintingOps method instead of generateArray - it is effectively the same, but
        // as you'll see from the code above it counts and prints the number of fundamental operations in each call.
        // As summing an array is a linear algorithm (for an array of length n, we need to look at every element
        // exactly once, hence n observations in total) you would expect the number of ops to be equal to the size of
        // the input. However, you can adapt this code for other non-linear algorithms (such as the sorting ones in the
        // lab exercises) to see how ops may scale differently, but there is no noise like there is with timing
        // experiments, and it is a fairer way to compare two algorithms.

        int[] input;
        int sum;
        long start, end, total, tenRunTotal;

        // no need for the cold start as we are counting ops rather than time now - the number of calculations will not
        // change based on whether the JVM has "warmed up" or not

        for (int arrayLength = 10000; arrayLength <= 1000000; arrayLength += 1000) {
            System.out.print(arrayLength + ",");

            // no need to do repetitions for this algorithm (sumArray) either as it will always do the same number of
            // calculations regardless of how the input is organised. This is NOT true for all algorithms though (e.g.
            // if you had a scanArray method that stopped when it found the desired value then it would have different
            // worst case and best case conditions - more on cases for analysis in week 2 though!). For now we will just
            // do the 10 repetitions still to demonstrate this (but we won't calculate an average any more as the
            // printing is internal to the method and a bit hacky with the way I've implemented this!)

            for (int repetition = 0; repetition < 10; repetition++) {
                input = generateArray(arrayLength);
                sum = sumArrayPrintingOps(input); // reminder - we have changed this to print the ops
                // no need to keep track of times - as an exercise you can do this yourself if you'd like to
            }
            System.out.println();
        }

        // Your output for experiment #5 should be:
        //
        //        10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,10000,
        //        11000,11000,11000,11000,11000,11000,11000,11000,11000,11000,11000,
        //        12000,12000,12000,12000,12000,12000,12000,12000,12000,12000,12000,
        //
        // Remember, the first value on each line is the size of the input, and then each of the values after that
        // on the same line is the number of fundamental operations carried out by the sumArrayPrintingOps method
        // for input of that size. As it is a linear algorithm, with 1 op per input, the output is n ops per execution
        // of the algorithm with an input size of n. This result is a bit boring to look at in this case but it
        // demonstrates that there is no noise and no need for the cold start here - we may wish to still do repetitions
        // for experiments like this (if the algorithm has different best and worst cases) but for sumArray we always
        // have to look at everything in the input array so the best and worst case (and therefore average case) are the
        // same.
        //
        // Plotting this data on a graph will confirm that sumArray is a linear algorithm
    }

    // I hope these experiments have been interesting and helped you understand how to do better timing experiments,
    // and it has also justified why we like to count operations in place of timing experiments where we can as it
    // leads to a fairer and more consistent analysis of an algorithm's preformance.
    //
    // If you'd like to go further, I suggest implementing the sorting algorithms in the lab exercises from week 1
    // and then recreating these experiments using insertion and selection sort - the results should be more
    // interesting. Also, which algorithm looks faster to you on average? We'll answer the question of which one is
    // faster later in the module but see if you can work that out from some experiments before we get that far!

}
