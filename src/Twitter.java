import java.util.*;
import java.util.stream.Collectors;

class Twitter {
    Map<Integer, List<Tweet>> feed;
    Map<Integer, Set<Integer>> follows;

    static class Tweet {
        int userid;
        int id;
        long timestamp;

        public Tweet(int u, int i) {
            userid = u;
            id = i;
            timestamp = System.nanoTime();
        }
    }

    /** Initialize your data structure here. */
    public Twitter() {
        feed = new HashMap<>();
        follows = new HashMap<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        Tweet t1 = new Tweet(userId, tweetId);
        List<Tweet> f = feed.getOrDefault(userId, new ArrayList<>());
        f.add(t1);
        feed.put(userId, f);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Tweet> nfeed = new ArrayList<>(feed.getOrDefault(userId, new ArrayList<>()));
        if (follows.containsKey(userId)) {
            Set<Integer> follow = follows.get(userId);
            for (int s : follow) {
                nfeed.addAll(feed.getOrDefault(s, new ArrayList<>()));
            }
        }
        nfeed.sort((o1, o2) -> (int) (o2.timestamp - o1.timestamp));
        List<Integer> feedIds = nfeed.stream().map(t -> t.id).limit(10).collect(Collectors.toList());
        return feedIds;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            Set<Integer> followees = follows.getOrDefault(followerId, new HashSet<>());
            followees.add(followeeId);
            follows.put(followerId, followees);
        } // else no-op
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) {
            Set<Integer> followees = follows.getOrDefault(followerId, new HashSet<>());
            followees.remove(followeeId);
            follows.put(followerId, followees);
        } // else no-op
    }

    public static void main(String[] args) {
        Twitter twit = new Twitter();
        twit.postTweet(1, 5);
        twit.postTweet(1, 3);
        System.out.println(twit.getNewsFeed(1));
//        twit.postTweet(1, 5);
//        System.out.println(twit.getNewsFeed(1));
//        twit.follow(1, 2);
//        twit.postTweet(2, 6);
//        System.out.println(twit.getNewsFeed(1));
//        twit.unfollow(1, 2);
//        System.out.println(twit.getNewsFeed(1));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */