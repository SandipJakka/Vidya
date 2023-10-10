package com.nomura.sandeep.chronicle.hackerrank.greedy;


/**
 * There are m jobs to schedule on n processors.
 * A schedule is balanced if the difference between the number of jobs scheduled on any two neighboring processors does not exceed 1.
 *
 *
 *
 * The kth processor is the most efficient,
 * and thus, the maximum number of jobs should be scheduled on that processor.
 * Find the maximum number of jobs that can be scheduled on the kth processor, such that the overall schedule remains balanced.
 *
 *
 *
 * Note: Each processor must have at least one job scheduled.
 *
 * Consider n = 5, m = 11, k = 3.
 *
                     Yes            Job on kth Processor
 * [1, 2, 3, 3, 2]	    Yes	        3
 * [2, 2, 2, 2, 3]	    Yes	        2
 *
 *
 * Given n = 5, m = 16, k = 2.
 *
 * One optimal job schedule is [4, 4, 3, 3, 2].
 *
 *
 *
 * Given n = 5, m = 11, k = 5.
 *
 * One optimal job schedule is [1, 1, 2, 3, 4].
 */
public class Scheduling {


    public static void main(String[] args) {
//        System.out.println("4=" + getMaximumJobs(5,11,5));
//        System.out.println("3=" + getMaximumJobs(5,11,3));
//        System.out.println("4=" + getMaximumJobs(5,16,2));
        System.out.println("439020=" + getMaximumJobs(808,354536233,234));
//        System.out.println("439020=" + getMaximumJobs(808,354536233,234));
    }


   /* public static int getMaximumJobs(int n, int m, int k) {
        int low = 1;
        int high = m;
        int ans = 0;
        // if kth node can't process x tasks, that means for any y , x>y can not be done.
        // so we need to go check mid and see if it satisfies the condition.
        while (high>= low){
            //take the mid
            int mid = (low + high) >> 1;
           // int mid = low + (high-low)/2;
            //check if m satisfies the case where 1 to k-1 and k+1 to n as follows
            // [...,max-2,max-1,max, max-1, max-2....]
            // check returns true if more tasks can be done or we achieve m number of tasks.
            if (check(mid, m, n,k)){
                // this could be the ans
                ans = mid ;
                //
                low = mid + 1;
            }else{
                //
                high = mid -1;
            }
        }
        return ans;
    }*/




        public static int getMaximumJobs(int n, int m, int k) {
            int ans = 0;
            int lo = 1;
            int hi = m;

            while (hi >= lo) {
                int mid = (lo + hi) / 2;
                if (check(mid, n, k, m)) {
                    ans = mid;
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }

            return ans;
        }

        public static boolean check(int mid, int n, int k, int m) {
            int prev = mid;
            int cur = mid;

            for (int i = k - 1; i > 0; i--) {
                prev = Math.max(1, prev - 1);
                cur += prev;
            }

            prev = mid;
            for (int i = k + 1; i <= n; i++) {
                prev = Math.max(1, prev - 1);
                cur += prev;
            }

            return cur <= m;
        }


    }



    /*public static boolean check(int mid , int m , int n, int k){
        int count = mid;
        int run = mid ;
        // run from K-1 to 1
        for (int i = k-1; i > 0 ; i--){
            run = Math.max(1,(run-1));
            count +=run;
        }
        run = mid;
        // run from k+1 to n
        for (int i = k+1; i < n+1 ; i++){
            run = Math.max(1,(run-1));
            count+=run;
        }
        // return true if we have reached the most jobs that can be done
        // or we can still do more jobs.
        return count <= m ;
    }*/

