import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int n = friends.length; 
        HashMap<String, Integer> dic = new HashMap<>();
        int[][] history = new int[n][n]; //주고받은 내역
        int[] score = new int[n];        //선물 지수
        int[] nextMonth = new int[n];    //다음 달에 받을 선물 개수
        
        for(int i = 0; i < n; i++) {
            dic.put(friends[i], i);
        }
        
        for(String gift : gifts) {
            String[] s = gift.split(" ");
            int giver = dic.get(s[0]);
            int receiver = dic.get(s[1]);
            
            history[giver][receiver]++;
            score[giver]++;
            score[receiver]--;
        }
        
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) continue;
                
                // 더 많은 선물을 준 사람이 존재
                if(history[i][j] > history[j][i]) {
                    nextMonth[i]++;
                }
                
                // 주고받은 기록이 없거나 같을때
                else if (history[i][j] == history[j][i]) {
                    if(score[i] > score[j]) nextMonth[i]++;
                }
                
            }
        }
        
        int answer = 0;
        
        for(int cnt : nextMonth) {
            if (cnt > answer) answer = cnt;
        }
 
        return answer;
    }
}