import java.util.Stack;

public class Strings {

    public static int indexOf(String haystack, String needle) {
        if(null == needle || needle.length() == 0) return 0;
        int found = -1;
        for(int h = 0; h < haystack.length(); h++) {
            found = -1;
            int n = 0;
            while(n < needle.length() && (h+n) < haystack.length()) {
                if(needle.charAt(n) == haystack.charAt(h+n)) {
                    n++;
                } else {
                    break;
                }
            }
            if(n == needle.length()) {
                found = h;
                break;
            }
        }
        return found;
    }

    public static boolean repeatedSubstringPattern(String s) {
        //base case
        if(s.length() == 1) return false;
        for(int i = 1; i <= s.length()/2; i++) {
            if(s.length() % i == 0) {
                String sub = s.substring(0, i); //substring is exclusive on upper bound i.e. i-1
                String[] parts = s.split(sub);
                if (parts.length == 0) return true;
            }
        }
        return false;
    }

    public static String validIPAddress(String IP) {
        String typ = "";
        if (IP.contains(".")) {
            //validate IPv4
            String[] parts = IP.split("\\.",-1);
            if (parts.length != 4) return "Neither";
            for(String p : parts) {
                try {
                    Integer num = Integer.parseInt(p);
                    // leading zeros are invalid
                    if (!num.toString().equals(p)) return "Neither";
                    if (num < 0 || num > 255) return "Neither";
                } catch (NumberFormatException nfe) {
                    return "Neither";
                }
            }
            typ = "IPv4";
        } else if (IP.contains(":")) {
            //validate IPv6
            String[] parts = IP.split(":",-1); //include empty trailing element
            if (parts.length != 8) return "Neither";
            for(String p : parts) {
                if (p.isEmpty() || p.length() > 4) return "Neither";
                // has to be hexadecimal
                for(char c : p.toCharArray()) {
                    if(Character.digit(c,16) < 0) return "Neither";
                }
            }
            typ = "IPv6";
        } else {
            return "Neither";
        }
        return typ;
    }

    public static String decodeString(String s) {
        if(s == null) return null;
        if(s.isEmpty()) return s;
        StringBuilder decoded = new StringBuilder();
        Stack<String> stack = new Stack<>();
        int index = 0;
        while(index < s.length()) {
            String c = s.substring(index, index+1);
            if (!c.equals("]")) {
                stack.push(c);
            } else {
                StringBuilder code = new StringBuilder();
                String popped = stack.pop();
                while(!popped.equals("[")) {
                    code.append(popped);
                    popped = stack.pop();
                }
                String codeStr = code.reverse().toString();
                // get number just by popping one more after encountering opening bracket
                int k = Integer.parseInt(stack.pop());
                StringBuilder repeats = new StringBuilder();
                while(k > 0) {
                    repeats.append(codeStr);
                    k--;
                }
                stack.push(repeats.toString());
            }
            index++;
        }
        // all decoded, just pop stack and append
        while (!stack.empty()) {
            decoded.insert(0, stack.pop());
        }
        return decoded.toString();
    }

    public static int findLUSlength(String[] strs) {
        int longest = Integer.MAX_VALUE;
        for(int i = 0; i < strs.length; i++) {
            String s1 = strs[i];
            for(int k = s1.length(); k > 0; k--) {
                String subseq1 = s1.substring(0, k);
                String subseq2 = s1.substring(s1.length()-k);
                for(int j = 0; j < strs.length; j++) {
                    if (i == j) continue;
                    if (strs[j].contains(subseq1) || strs[j].contains(subseq2)) break;
                    else {
                        longest = Math.min(longest, k);
                    }
                }
            }
        }
        return longest;
    }

    public static void main(String[] args) {

        System.out.println(indexOf("mississippi", "issip"));

        System.out.println(repeatedSubstringPattern("xsuloikroqamjxhvndallxkfytwttvvrpqegaqxogzdqyknlpqyanifldeezbcstswzsnyjvxmnvybinrgngqurtyxuazmvrlumyuvvrjjohmwufchlsbencbieduzwftifpfdgomwvasphwyuoaybebjieghdncltrryzfmvdmnuvjtpzvpczqohxuqcjsnelgckmofrculxfemgbdicwyjvsbxetkierlqfbxyetjbyqqgrtrurpfmkhjocwyjpjzunxsrqdurtkxngqjxgokqxvgarejgqkadhuuayortbqgjhpgpgsfdolffrqmhbokklgklxdeywnhfepukibqwoxbfqpnrgzdrgocdtidpxmucbqojrghfelnuaangzszhibmcmptjbqnfgcoykyfojskluzuwstdaxqejhyuloiqumguwecnnuzbpzvntoqvliawsatdobtkpzhlejytfauwzrjugcptmrserlhhoaudcboimpdrpaqqrzmxddtqvluoweymgspitfshwwynwqfnqrnvvilstiirmgduyuftzxawvbjrrphjiwffgszzcisqoxlprqkqnloloaehrltzjahpsgqxoknfhywyogrethphhtrahkcsmjkgpcdgnrnwpjxgpqkjxbshwlhfxjyjskqkmtqbkdycovidnuokvjrtubzukzdfjtpxphzzmzbawlfjfuvcfpwbqxvcyzhhuygjhhltgoteaznhvlkaaidqhzsfacoucwekerfmfzrhagpxrbxtlajsbezbgnwklcupvaeabviddxaxazqlbcddgcgoreacixudzyeavofanfxngqyhubmaftqyzqcinylowrotfvusctfijdsdggfnbxnbqsjfqwupokitgcmiwtthxlnfruvtsiuiafprbjgpuqlatxkrsdyronxiisbacxkdczwdlevfughpftgxzhpnuoxegagixsnbujffpcmkivbpoimnrddnrcuzdakatxcnjjsangmxbomryahpekexmyzrzjsuiwjrfduujgrkuddsfkjjwqjjoiaptulbquvxxprgvksqnwktiwefmpqczsusnfufarfxgygbjatywgthcamqpcsrumjjufpuwwteubifcbeajzhnzvdrxyismtdgbscxqyclzksdnwgzypmxlsqisaceuglvapurnyepkwuavaztqnsbhjlzjoefurcwgznwxtliqfklileyywbihmhtanywebvnakjzewjudthlenlflontbumdimcopxbrhmrlkahqwqdafphrfumgrakzmmpclttshmgsnpilgllncteipqqgschfoxjbqcuzrcrerbrzpcnrxtbpmsveudjlcsmuxitoknueonfdpsxpmaeyubepgociiqehbyxlltrbgxfypepdevdzwiqdyungksqlqnzdjqepnlpfrekwzoxwynbwjqetiuhakidtykkoxavpefngvketzfpivudgqkgasmvtygjxiemmjzuhlyakfsudoyjekrhffcydkjbsnphyrdfcciphajkojvsunbzsezyqiblvquvjxbobjdjjovzyrruettyzswraxexqyszyvnzgsirjeqjxkdbfwzeqyxqxcpnchpafcclxkdgqtpndsqkqsqgqoynsnduwsxbwznvlsbensttmkdceukuiijaxowugtxfukageeksydllpontiansizuinrcwmbdhofnslzkkcvvsmknukdpvcjdrchppiuyyalrlmbxqzsilfyhpbwmdgrwiaozjixhikawwctndoxotvvkwsxbaoyipmiaufjfqmdooybtmzhfwestwpuwfuhwipyfwkkowpdknedafmhcqzxpqrnyouuwlvbifnybcbnqisspezrwbqlrkbryvoujjytonrpqveidwauczoycemwonnmkbjvkjoawgrmdvwirwctyquaviylxmyhpjfcstcqbufepbmjyreyhyaeizqfnqpczugtnjjrfovwqkfqbmqimqpvrfaevrjulppbaysfnpefzlxcxnnkdcfvjhbezkamcppenhkczqjabgdztsjxuqudxxjyijlmaaqpkculsdlwkqvdigceywjvuswedjsfjivvnpihsudcmyixgkbejncffyllhdqsnnqacyixulbjmjguujzdwdkebvrkyhcnxsoztyynjawxjesfotkmwuszpjjptldqgtrzrqocslabruhfntslomkfwtvpgahmtynkqemirokiktoxoikcuuispfjwhqkapymhcaqtpcdzvrzlbloygyqvfatmseoheapiahdrjgtgyurbfhzlfsjmucymimwmonupuxideblevcfdkhorbzevecjmrfzfutworhtxiebgjijtrzksbqpfvzrmgqtbmsrfzlcjrdpanavfcizdypitqzhysbzettjsgnbxstafvxkxfmaebizrkhpciridmtxsgaryvtfrrsowepchzcajjrntivyuqvoyxgytzekswypcptjpvjagaobllzhewwcuebnffxydxphpiqggbgobwsoktwoquqkarzfyyfdxukmfasldbbhxtvhntxdpopphqxgqejnbwcgyutcxujvqgedacirszjzrihhmtunpwtptmrqjcnzjuelymcqnlretbliuhvlgrazdurowhnfbrszfvrsiiwejjmfrjsxavygprunykvbdnclitdtccxnnlveundxiddzvznvlkxpxbcivfbzjamznjfjxjgholjllyhwignfhpnexqdisshfbnpayximjtyurhsbniiwxwbwogfhyomabdazbkokdmgwtyxiggggejcuqjboyqqztrbudpehdmfylfitnobraglqppepfvryouvrlbaoogoblwwamyrmgeexvjnagebuyynjzoznwnqjlnjzncotiuqefdgljjhjhxuhltwjinsvadxnfngnhytncaynlixtfrasnwbxlvtlysaffgumhqzjqaydkvwppyjcxmzihuuhmworfigmyhvqhtutrskgrtkofjwogsvspjmuusguzdnjyhcrbnxsijiovfcbzavojhkfzsabvnkwwmnxbnskwaarhcuhcqxoxktzcvaaikwgylgayymxymqiqlptbojyycxbyzdijbpimvknvykvkadalsvohepfjoiholrxmihbftoarawdazeoubedgtkpityygpvvafwfymgsmcvodbexdjclencogamorkjrphxpvfjhjfymiemnpoyubakjcxawjlpatopotzycgyrocdcstcovfygjxghgsxgnnybhzblfbcvehzybemyjkesilaqfzxsmxvvwxhdylaevacweowpbajlnlbskamzxiuthdyhpzmkzoiffewggftwruqdxdculxquaobrnndoelvjrrrazkwqgotdrwyevqyveanupskteiexzvxyfvqmmkrfipkdfkammpieisxmaclczlcfgvtsfkypcksjwsxlifpqaoecsrescjzjeudlgdckpuuoorzyzqqzgwbzoitqtubzqhmwkohckdqywpzvhrwpoaqekjudfycvfqsrebtogjmcanajfyzvypzibtngtrcahwslznzfxojzdljimkdkzcvaykjtcnuljmzkzaryocauolcavimumjxmuggyvaleokqpljqblqvbbadwisswbrjxzrvurjahtswebminedofdudnmymnpvnrmklftoongktizbxdfllkhoatmmjejwvxgieayspfeipfkmjrzwbhmdazpjfahczokdqlxfqcnfhxrsoqqszdvacnljpvlpjxubisgawvfftyajrqdqtzsxoggdbtumtpndjvehqlmjkmvdfytyzoynugtxapjveyyptcwjqlvczsfeqafsodhkjahlqqgwofojjmtzzqbzigfajmsxpaibcrouivtycjknvdnxtqydzddiqyhharkejbztwutaddnaicxdvvgubmetklckfpoqqpjojeqoifrqiqwfocbpofkasomzdbpvsajefbsnhlfrcbwqgxhwctxwhjwnmlifmujtvdicpnttrrjzosvslursenhsaqzomihhopmfffywxjxnbsgonzitmqloilduvkblansfvqdubahcupshobccrqrzduvaewogmglhxqbyqkdlaxuqyztrrksuenwkmkryrefqvionstautxlrwelnqdjurfgdsxdjekzzspomlphemuvkagzqmimfpskhsnqwxrxrhlfucmyoivyiexyqhkxxfhgxuzvtqsmzfrtigtyopxztwxlgkifpplmlkjgddseilasrtriiqhdoeouqwzfnntyzcgefyjqkjideblptusibjerhgxvtsvsvexacisotbuxehoychifsowzuvwnouilyhlcpgtkjnhnwwnyfvfzkcygzxwnteyrnmvcsombdvqateaabgznkaujzqzswzjjlenfvbiywbasfbugjuutwqevgdncdylqeyyzzgaxharodmxoithejobprotumenselsftseduwrakoosjyysmzhfpccdgpvmaptvhyyewsggiuasuakgzumqwotffqrhglcpldthvzpdwpvqpizqclgabbfgrznxmrnzuigpkxvgosyfaxxeidflgmrzngzzymyswgkgdfotxnyakvevalgiyailghngvnbtulazsqvpftrqrwnrhtahvkvcrkkoxlhtyjvsaqifjbxaxkuhgwqbglfzvqnvduoeejwgzgnlinnhzhofffhlsokqgxlkuzqalmimvxxdknkkwbrcganapaqvzbhtdxvomdahdamnnwzjzrlhtbiidygccnyfntvbzviexurkstwsmjzfkjqniwsmlqralmbmjlqjfkvadrurbvwnfobpmvbyluawicltnbcvnxsuloikroqamjxhvndallxkfytwttvvrpqegaqxogzdqyknlpqyanifldeezbcstswzsnyjvxmnvybinrgngqurtyxuazmvrlumyuvvrjjohmwufchlsbencbieduzwftifpfdgomwvasphwyuoaybebjieghdncltrryzfmvdmnuvjtpzvpczqohxuqcjsnelgckmofrculxfemgbdicwyjvsbxetkierlqfbxyetjbyqqgrtrurpfmkhjocwyjpjzunxsrqdurtkxngqjxgokqxvgarejgqkadhuuayortbqgjhpgpgsfdolffrqmhbokklgklxdeywnhfepukibqwoxbfqpnrgzdrgocdtidpxmucbqojrghfelnuaangzszhibmcmptjbqnfgcoykyfojskluzuwstdaxqejhyuloiqumguwecnnuzbpzvntoqvliawsatdobtkpzhlejytfauwzrjugcptmrserlhhoaudcboimpdrpaqqrzmxddtqvluoweymgspitfshwwynwqfnqrnvvilstiirmgduyuftzxawvbjrrphjiwffgszzcisqoxlprqkqnloloaehrltzjahpsgqxoknfhywyogrethphhtrahkcsmjkgpcdgnrnwpjxgpqkjxbshwlhfxjyjskqkmtqbkdycovidnuokvjrtubzukzdfjtpxphzzmzbawlfjfuvcfpwbqxvcyzhhuygjhhltgoteaznhvlkaaidqhzsfacoucwekerfmfzrhagpxrbxtlajsbezbgnwklcupvaeabviddxaxazqlbcddgcgoreacixudzyeavofanfxngqyhubmaftqyzqcinylowrotfvusctfijdsdggfnbxnbqsjfqwupokitgcmiwtthxlnfruvtsiuiafprbjgpuqlatxkrsdyronxiisbacxkdczwdlevfughpftgxzhpnuoxegagixsnbujffpcmkivbpoimnrddnrcuzdakatxcnjjsangmxbomryahpekexmyzrzjsuiwjrfduujgrkuddsfkjjwqjjoiaptulbquvxxprgvksqnwktiwefmpqczsusnfufarfxgygbjatywgthcamqpcsrumjjufpuwwteubifcbeajzhnzvdrxyismtdgbscxqyclzksdnwgzypmxlsqisaceuglvapurnyepkwuavaztqnsbhjlzjoefurcwgznwxtliqfklileyywbihmhtanywebvnakjzewjudthlenlflontbumdimcopxbrhmrlkahqwqdafphrfumgrakzmmpclttshmgsnpilgllncteipqqgschfoxjbqcuzrcrerbrzpcnrxtbpmsveudjlcsmuxitoknueonfdpsxpmaeyubepgociiqehbyxlltrbgxfypepdevdzwiqdyungksqlqnzdjqepnlpfrekwzoxwynbwjqetiuhakidtykkoxavpefngvketzfpivudgqkgasmvtygjxiemmjzuhlyakfsudoyjekrhffcydkjbsnphyrdfcciphajkojvsunbzsezyqiblvquvjxbobjdjjovzyrruettyzswraxexqyszyvnzgsirjeqjxkdbfwzeqyxqxcpnchpafcclxkdgqtpndsqkqsqgqoynsnduwsxbwznvlsbensttmkdceukuiijaxowugtxfukageeksydllpontiansizuinrcwmbdhofnslzkkcvvsmknukdpvcjdrchppiuyyalrlmbxqzsilfyhpbwmdgrwiaozjixhikawwctndoxotvvkwsxbaoyipmiaufjfqmdooybtmzhfwestwpuwfuhwipyfwkkowpdknedafmhcqzxpqrnyouuwlvbifnybcbnqisspezrwbqlrkbryvoujjytonrpqveidwauczoycemwonnmkbjvkjoawgrmdvwirwctyquaviylxmyhpjfcstcqbufepbmjyreyhyaeizqfnqpczugtnjjrfovwqkfqbmqimqpvrfaevrjulppbaysfnpefzlxcxnnkdcfvjhbezkamcppenhkczqjabgdztsjxuqudxxjyijlmaaqpkculsdlwkqvdigceywjvuswedjsfjivvnpihsudcmyixgkbejncffyllhdqsnnqacyixulbjmjguujzdwdkebvrkyhcnxsoztyynjawxjesfotkmwuszpjjptldqgtrzrqocslabruhfntslomkfwtvpgahmtynkqemirokiktoxoikcuuispfjwhqkapymhcaqtpcdzvrzlbloygyqvfatmseoheapiahdrjgtgyurbfhzlfsjmucymimwmonupuxideblevcfdkhorbzevecjmrfzfutworhtxiebgjijtrzksbqpfvzrmgqtbmsrfzlcjrdpanavfcizdypitqzhysbzettjsgnbxstafvxkxfmaebizrkhpciridmtxsgaryvtfrrsowepchzcajjrntivyuqvoyxgytzekswypcptjpvjagaobllzhewwcuebnffxydxphpiqggbgobwsoktwoquqkarzfyyfdxukmfasldbbhxtvhntxdpopphqxgqejnbwcgyutcxujvqgedacirszjzrihhmtunpwtptmrqjcnzjuelymcqnlretbliuhvlgrazdurowhnfbrszfvrsiiwejjmfrjsxavygprunykvbdnclitdtccxnnlveundxiddzvznvlkxpxbcivfbzjamznjfjxjgholjllyhwignfhpnexqdisshfbnpayximjtyurhsbniiwxwbwogfhyomabdazbkokdmgwtyxiggggejcuqjboyqqztrbudpehdmfylfitnobraglqppepfvryouvrlbaoogoblwwamyrmgeexvjnagebuyynjzoznwnqjlnjzncotiuqefdgljjhjhxuhltwjinsvadxnfngnhytncaynlixtfrasnwbxlvtlysaffgumhqzjqaydkvwppyjcxmzihuuhmworfigmyhvqhtutrskgrtkofjwogsvspjmuusguzdnjyhcrbnxsijiovfcbzavojhkfzsabvnkwwmnxbnskwaarhcuhcqxoxktzcvaaikwgylgayymxymqiqlptbojyycxbyzdijbpimvknvykvkadalsvohepfjoiholrxmihbftoarawdazeoubedgtkpityygpvvafwfymgsmcvodbexdjclencogamorkjrphxpvfjhjfymiemnpoyubakjcxawjlpatopotzycgyrocdcstcovfygjxghgsxgnnybhzblfbcvehzybemyjkesilaqfzxsmxvvwxhdylaevacweowpbajlnlbskamzxiuthdyhpzmkzoiffewggftwruqdxdculxquaobrnndoelvjrrrazkwqgotdrwyevqyveanupskteiexzvxyfvqmmkrfipkdfkammpieisxmaclczlcfgvtsfkypcksjwsxlifpqaoecsrescjzjeudlgdckpuuoorzyzqqzgwbzoitqtubzqhmwkohckdqywpzvhrwpoaqekjudfycvfqsrebtogjmcanajfyzvypzibtngtrcahwslznzfxojzdljimkdkzcvaykjtcnuljmzkzaryocauolcavimumjxmuggyvaleokqpljqblqvbbadwisswbrjxzrvurjahtswebminedofdudnmymnpvnrmklftoongktizbxdfllkhoatmmjejwvxgieayspfeipfkmjrzwbhmdazpjfahczokdqlxfqcnfhxrsoqqszdvacnljpvlpjxubisgawvfftyajrqdqtzsxoggdbtumtpndjvehqlmjkmvdfytyzoynugtxapjveyyptcwjqlvczsfeqafsodhkjahlqqgwofojjmtzzqbzigfajmsxpaibcrouivtycjknvdnxtqydzddiqyhharkejbztwutaddnaicxdvvgubmetklckfpoqqpjojeqoifrqiqwfocbpofkasomzdbpvsajefbsnhlfrcbwqgxhwctxwhjwnmlifmujtvdicpnttrrjzosvslursenhsaqzomihhopmfffywxjxnbsgonzitmqloilduvkblansfvqdubahcupshobccrqrzduvaewogmglhxqbyqkdlaxuqyztrrksuenwkmkryrefqvionstautxlrwelnqdjurfgdsxdjekzzspomlphemuvkagzqmimfpskhsnqwxrxrhlfucmyoivyiexyqhkxxfhgxuzvtqsmzfrtigtyopxztwxlgkifpplmlkjgddseilasrtriiqhdoeouqwzfnntyzcgefyjqkjideblptusibjerhgxvtsvsvexacisotbuxehoychifsowzuvwnouilyhlcpgtkjnhnwwnyfvfzkcygzxwnteyrnmvcsombdvqateaabgznkaujzqzswzjjlenfvbiywbasfbugjuutwqevgdncdylqeyyzzgaxharodmxoithejobprotumenselsftseduwrakoosjyysmzhfpccdgpvmaptvhyyewsggiuasuakgzumqwotffqrhglcpldthvzpdwpvqpizqclgabbfgrznxmrnzuigpkxvgosyfaxxeidflgmrzngzzymyswgkgdfotxnyakvevalgiyailghngvnbtulazsqvpftrqrwnrhtahvkvcrkkoxlhtyjvsaqifjbxaxkuhgwqbglfzvqnvduoeejwgzgnlinnhzhofffhlsokqgxlkuzqalmimvxxdknkkwbrcganapaqvzbhtdxvomdahdamnnwzjzrlhtbiidygccnyfntvbzviexurkstwsmjzfkjqniwsmlqralmbmjlqjfkvadrurbvwnfobpmvbyluawicltnbcvn"));
        // System.out.println(repeatedSubstringPattern("abcabcabcd"));
        String ip = "2001:0db8:85a3:0:0:8A2E:0370:7334:";
        System.out.println(ip.split(":",-1).length);
//        for(char c : ip.toCharArray()) {
//            System.out.println(Character.digit(c, 16));
//
//        }
        System.out.println(validIPAddress("01.01.01.01"));
        System.out.println(Integer.parseInt("01"));

        System.out.println(decodeString("3[a]2[bc]"));
        System.out.println(decodeString("3[a2[c]]"));

        System.out.println(findLUSlength(new String[]{"aba","cdc","eae"}));
    }

}
