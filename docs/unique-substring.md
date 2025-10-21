    public static int maxDistinctSubstringLengthInSessions(String test) {
        HashSet<Character> charSet = new HashSet<>();
        Stack<Integer> count = new Stack<>();
        int max = 0, c = 0, n = test.length();
        
        for (int i = 0; i < n; i++) {
            if(test.charAt(i)=='*'){           // handles delimeter
                count.push(charSet.size());   // handles count
                count.clear();
                continue;
            }
            charSet.add(test.charAt(i));       // handles duplicates
        }
        while(!count.isEmpty()){
            c = count.pop();
            max = (c > max) ? c : max;
        }
        return max;
    }