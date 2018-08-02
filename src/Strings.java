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

    public static void main(String[] args) {

        System.out.println(indexOf("mississippi", "issip"));

        System.out.println(repeatedSubstringPattern("xsuloikroqamjxhvndallxkfytwttvvrpqegaqxogzdqyknlpqyanifldeezbcstswzsnyjvxmnvybinrgngqurtyxuazmvrlumyuvvrjjohmwufchlsbencbieduzwftifpfdgomwvasphwyuoaybebjieghdncltrryzfmvdmnuvjtpzvpczqohxuqcjsnelgckmofrculxfemgbdicwyjvsbxetkierlqfbxyetjbyqqgrtrurpfmkhjocwyjpjzunxsrqdurtkxngqjxgokqxvgarejgqkadhuuayortbqgjhpgpgsfdolffrqmhbokklgklxdeywnhfepukibqwoxbfqpnrgzdrgocdtidpxmucbqojrghfelnuaangzszhibmcmptjbqnfgcoykyfojskluzuwstdaxqejhyuloiqumguwecnnuzbpzvntoqvliawsatdobtkpzhlejytfauwzrjugcptmrserlhhoaudcboimpdrpaqqrzmxddtqvluoweymgspitfshwwynwqfnqrnvvilstiirmgduyuftzxawvbjrrphjiwffgszzcisqoxlprqkqnloloaehrltzjahpsgqxoknfhywyogrethphhtrahkcsmjkgpcdgnrnwpjxgpqkjxbshwlhfxjyjskqkmtqbkdycovidnuokvjrtubzukzdfjtpxphzzmzbawlfjfuvcfpwbqxvcyzhhuygjhhltgoteaznhvlkaaidqhzsfacoucwekerfmfzrhagpxrbxtlajsbezbgnwklcupvaeabviddxaxazqlbcddgcgoreacixudzyeavofanfxngqyhubmaftqyzqcinylowrotfvusctfijdsdggfnbxnbqsjfqwupokitgcmiwtthxlnfruvtsiuiafprbjgpuqlatxkrsdyronxiisbacxkdczwdlevfughpftgxzhpnuoxegagixsnbujffpcmkivbpoimnrddnrcuzdakatxcnjjsangmxbomryahpekexmyzrzjsuiwjrfduujgrkuddsfkjjwqjjoiaptulbquvxxprgvksqnwktiwefmpqczsusnfufarfxgygbjatywgthcamqpcsrumjjufpuwwteubifcbeajzhnzvdrxyismtdgbscxqyclzksdnwgzypmxlsqisaceuglvapurnyepkwuavaztqnsbhjlzjoefurcwgznwxtliqfklileyywbihmhtanywebvnakjzewjudthlenlflontbumdimcopxbrhmrlkahqwqdafphrfumgrakzmmpclttshmgsnpilgllncteipqqgschfoxjbqcuzrcrerbrzpcnrxtbpmsveudjlcsmuxitoknueonfdpsxpmaeyubepgociiqehbyxlltrbgxfypepdevdzwiqdyungksqlqnzdjqepnlpfrekwzoxwynbwjqetiuhakidtykkoxavpefngvketzfpivudgqkgasmvtygjxiemmjzuhlyakfsudoyjekrhffcydkjbsnphyrdfcciphajkojvsunbzsezyqiblvquvjxbobjdjjovzyrruettyzswraxexqyszyvnzgsirjeqjxkdbfwzeqyxqxcpnchpafcclxkdgqtpndsqkqsqgqoynsnduwsxbwznvlsbensttmkdceukuiijaxowugtxfukageeksydllpontiansizuinrcwmbdhofnslzkkcvvsmknukdpvcjdrchppiuyyalrlmbxqzsilfyhpbwmdgrwiaozjixhikawwctndoxotvvkwsxbaoyipmiaufjfqmdooybtmzhfwestwpuwfuhwipyfwkkowpdknedafmhcqzxpqrnyouuwlvbifnybcbnqisspezrwbqlrkbryvoujjytonrpqveidwauczoycemwonnmkbjvkjoawgrmdvwirwctyquaviylxmyhpjfcstcqbufepbmjyreyhyaeizqfnqpczugtnjjrfovwqkfqbmqimqpvrfaevrjulppbaysfnpefzlxcxnnkdcfvjhbezkamcppenhkczqjabgdztsjxuqudxxjyijlmaaqpkculsdlwkqvdigceywjvuswedjsfjivvnpihsudcmyixgkbejncffyllhdqsnnqacyixulbjmjguujzdwdkebvrkyhcnxsoztyynjawxjesfotkmwuszpjjptldqgtrzrqocslabruhfntslomkfwtvpgahmtynkqemirokiktoxoikcuuispfjwhqkapymhcaqtpcdzvrzlbloygyqvfatmseoheapiahdrjgtgyurbfhzlfsjmucymimwmonupuxideblevcfdkhorbzevecjmrfzfutworhtxiebgjijtrzksbqpfvzrmgqtbmsrfzlcjrdpanavfcizdypitqzhysbzettjsgnbxstafvxkxfmaebizrkhpciridmtxsgaryvtfrrsowepchzcajjrntivyuqvoyxgytzekswypcptjpvjagaobllzhewwcuebnffxydxphpiqggbgobwsoktwoquqkarzfyyfdxukmfasldbbhxtvhntxdpopphqxgqejnbwcgyutcxujvqgedacirszjzrihhmtunpwtptmrqjcnzjuelymcqnlretbliuhvlgrazdurowhnfbrszfvrsiiwejjmfrjsxavygprunykvbdnclitdtccxnnlveundxiddzvznvlkxpxbcivfbzjamznjfjxjgholjllyhwignfhpnexqdisshfbnpayximjtyurhsbniiwxwbwogfhyomabdazbkokdmgwtyxiggggejcuqjboyqqztrbudpehdmfylfitnobraglqppepfvryouvrlbaoogoblwwamyrmgeexvjnagebuyynjzoznwnqjlnjzncotiuqefdgljjhjhxuhltwjinsvadxnfngnhytncaynlixtfrasnwbxlvtlysaffgumhqzjqaydkvwppyjcxmzihuuhmworfigmyhvqhtutrskgrtkofjwogsvspjmuusguzdnjyhcrbnxsijiovfcbzavojhkfzsabvnkwwmnxbnskwaarhcuhcqxoxktzcvaaikwgylgayymxymqiqlptbojyycxbyzdijbpimvknvykvkadalsvohepfjoiholrxmihbftoarawdazeoubedgtkpityygpvvafwfymgsmcvodbexdjclencogamorkjrphxpvfjhjfymiemnpoyubakjcxawjlpatopotzycgyrocdcstcovfygjxghgsxgnnybhzblfbcvehzybemyjkesilaqfzxsmxvvwxhdylaevacweowpbajlnlbskamzxiuthdyhpzmkzoiffewggftwruqdxdculxquaobrnndoelvjrrrazkwqgotdrwyevqyveanupskteiexzvxyfvqmmkrfipkdfkammpieisxmaclczlcfgvtsfkypcksjwsxlifpqaoecsrescjzjeudlgdckpuuoorzyzqqzgwbzoitqtubzqhmwkohckdqywpzvhrwpoaqekjudfycvfqsrebtogjmcanajfyzvypzibtngtrcahwslznzfxojzdljimkdkzcvaykjtcnuljmzkzaryocauolcavimumjxmuggyvaleokqpljqblqvbbadwisswbrjxzrvurjahtswebminedofdudnmymnpvnrmklftoongktizbxdfllkhoatmmjejwvxgieayspfeipfkmjrzwbhmdazpjfahczokdqlxfqcnfhxrsoqqszdvacnljpvlpjxubisgawvfftyajrqdqtzsxoggdbtumtpndjvehqlmjkmvdfytyzoynugtxapjveyyptcwjqlvczsfeqafsodhkjahlqqgwofojjmtzzqbzigfajmsxpaibcrouivtycjknvdnxtqydzddiqyhharkejbztwutaddnaicxdvvgubmetklckfpoqqpjojeqoifrqiqwfocbpofkasomzdbpvsajefbsnhlfrcbwqgxhwctxwhjwnmlifmujtvdicpnttrrjzosvslursenhsaqzomihhopmfffywxjxnbsgonzitmqloilduvkblansfvqdubahcupshobccrqrzduvaewogmglhxqbyqkdlaxuqyztrrksuenwkmkryrefqvionstautxlrwelnqdjurfgdsxdjekzzspomlphemuvkagzqmimfpskhsnqwxrxrhlfucmyoivyiexyqhkxxfhgxuzvtqsmzfrtigtyopxztwxlgkifpplmlkjgddseilasrtriiqhdoeouqwzfnntyzcgefyjqkjideblptusibjerhgxvtsvsvexacisotbuxehoychifsowzuvwnouilyhlcpgtkjnhnwwnyfvfzkcygzxwnteyrnmvcsombdvqateaabgznkaujzqzswzjjlenfvbiywbasfbugjuutwqevgdncdylqeyyzzgaxharodmxoithejobprotumenselsftseduwrakoosjyysmzhfpccdgpvmaptvhyyewsggiuasuakgzumqwotffqrhglcpldthvzpdwpvqpizqclgabbfgrznxmrnzuigpkxvgosyfaxxeidflgmrzngzzymyswgkgdfotxnyakvevalgiyailghngvnbtulazsqvpftrqrwnrhtahvkvcrkkoxlhtyjvsaqifjbxaxkuhgwqbglfzvqnvduoeejwgzgnlinnhzhofffhlsokqgxlkuzqalmimvxxdknkkwbrcganapaqvzbhtdxvomdahdamnnwzjzrlhtbiidygccnyfntvbzviexurkstwsmjzfkjqniwsmlqralmbmjlqjfkvadrurbvwnfobpmvbyluawicltnbcvnxsuloikroqamjxhvndallxkfytwttvvrpqegaqxogzdqyknlpqyanifldeezbcstswzsnyjvxmnvybinrgngqurtyxuazmvrlumyuvvrjjohmwufchlsbencbieduzwftifpfdgomwvasphwyuoaybebjieghdncltrryzfmvdmnuvjtpzvpczqohxuqcjsnelgckmofrculxfemgbdicwyjvsbxetkierlqfbxyetjbyqqgrtrurpfmkhjocwyjpjzunxsrqdurtkxngqjxgokqxvgarejgqkadhuuayortbqgjhpgpgsfdolffrqmhbokklgklxdeywnhfepukibqwoxbfqpnrgzdrgocdtidpxmucbqojrghfelnuaangzszhibmcmptjbqnfgcoykyfojskluzuwstdaxqejhyuloiqumguwecnnuzbpzvntoqvliawsatdobtkpzhlejytfauwzrjugcptmrserlhhoaudcboimpdrpaqqrzmxddtqvluoweymgspitfshwwynwqfnqrnvvilstiirmgduyuftzxawvbjrrphjiwffgszzcisqoxlprqkqnloloaehrltzjahpsgqxoknfhywyogrethphhtrahkcsmjkgpcdgnrnwpjxgpqkjxbshwlhfxjyjskqkmtqbkdycovidnuokvjrtubzukzdfjtpxphzzmzbawlfjfuvcfpwbqxvcyzhhuygjhhltgoteaznhvlkaaidqhzsfacoucwekerfmfzrhagpxrbxtlajsbezbgnwklcupvaeabviddxaxazqlbcddgcgoreacixudzyeavofanfxngqyhubmaftqyzqcinylowrotfvusctfijdsdggfnbxnbqsjfqwupokitgcmiwtthxlnfruvtsiuiafprbjgpuqlatxkrsdyronxiisbacxkdczwdlevfughpftgxzhpnuoxegagixsnbujffpcmkivbpoimnrddnrcuzdakatxcnjjsangmxbomryahpekexmyzrzjsuiwjrfduujgrkuddsfkjjwqjjoiaptulbquvxxprgvksqnwktiwefmpqczsusnfufarfxgygbjatywgthcamqpcsrumjjufpuwwteubifcbeajzhnzvdrxyismtdgbscxqyclzksdnwgzypmxlsqisaceuglvapurnyepkwuavaztqnsbhjlzjoefurcwgznwxtliqfklileyywbihmhtanywebvnakjzewjudthlenlflontbumdimcopxbrhmrlkahqwqdafphrfumgrakzmmpclttshmgsnpilgllncteipqqgschfoxjbqcuzrcrerbrzpcnrxtbpmsveudjlcsmuxitoknueonfdpsxpmaeyubepgociiqehbyxlltrbgxfypepdevdzwiqdyungksqlqnzdjqepnlpfrekwzoxwynbwjqetiuhakidtykkoxavpefngvketzfpivudgqkgasmvtygjxiemmjzuhlyakfsudoyjekrhffcydkjbsnphyrdfcciphajkojvsunbzsezyqiblvquvjxbobjdjjovzyrruettyzswraxexqyszyvnzgsirjeqjxkdbfwzeqyxqxcpnchpafcclxkdgqtpndsqkqsqgqoynsnduwsxbwznvlsbensttmkdceukuiijaxowugtxfukageeksydllpontiansizuinrcwmbdhofnslzkkcvvsmknukdpvcjdrchppiuyyalrlmbxqzsilfyhpbwmdgrwiaozjixhikawwctndoxotvvkwsxbaoyipmiaufjfqmdooybtmzhfwestwpuwfuhwipyfwkkowpdknedafmhcqzxpqrnyouuwlvbifnybcbnqisspezrwbqlrkbryvoujjytonrpqveidwauczoycemwonnmkbjvkjoawgrmdvwirwctyquaviylxmyhpjfcstcqbufepbmjyreyhyaeizqfnqpczugtnjjrfovwqkfqbmqimqpvrfaevrjulppbaysfnpefzlxcxnnkdcfvjhbezkamcppenhkczqjabgdztsjxuqudxxjyijlmaaqpkculsdlwkqvdigceywjvuswedjsfjivvnpihsudcmyixgkbejncffyllhdqsnnqacyixulbjmjguujzdwdkebvrkyhcnxsoztyynjawxjesfotkmwuszpjjptldqgtrzrqocslabruhfntslomkfwtvpgahmtynkqemirokiktoxoikcuuispfjwhqkapymhcaqtpcdzvrzlbloygyqvfatmseoheapiahdrjgtgyurbfhzlfsjmucymimwmonupuxideblevcfdkhorbzevecjmrfzfutworhtxiebgjijtrzksbqpfvzrmgqtbmsrfzlcjrdpanavfcizdypitqzhysbzettjsgnbxstafvxkxfmaebizrkhpciridmtxsgaryvtfrrsowepchzcajjrntivyuqvoyxgytzekswypcptjpvjagaobllzhewwcuebnffxydxphpiqggbgobwsoktwoquqkarzfyyfdxukmfasldbbhxtvhntxdpopphqxgqejnbwcgyutcxujvqgedacirszjzrihhmtunpwtptmrqjcnzjuelymcqnlretbliuhvlgrazdurowhnfbrszfvrsiiwejjmfrjsxavygprunykvbdnclitdtccxnnlveundxiddzvznvlkxpxbcivfbzjamznjfjxjgholjllyhwignfhpnexqdisshfbnpayximjtyurhsbniiwxwbwogfhyomabdazbkokdmgwtyxiggggejcuqjboyqqztrbudpehdmfylfitnobraglqppepfvryouvrlbaoogoblwwamyrmgeexvjnagebuyynjzoznwnqjlnjzncotiuqefdgljjhjhxuhltwjinsvadxnfngnhytncaynlixtfrasnwbxlvtlysaffgumhqzjqaydkvwppyjcxmzihuuhmworfigmyhvqhtutrskgrtkofjwogsvspjmuusguzdnjyhcrbnxsijiovfcbzavojhkfzsabvnkwwmnxbnskwaarhcuhcqxoxktzcvaaikwgylgayymxymqiqlptbojyycxbyzdijbpimvknvykvkadalsvohepfjoiholrxmihbftoarawdazeoubedgtkpityygpvvafwfymgsmcvodbexdjclencogamorkjrphxpvfjhjfymiemnpoyubakjcxawjlpatopotzycgyrocdcstcovfygjxghgsxgnnybhzblfbcvehzybemyjkesilaqfzxsmxvvwxhdylaevacweowpbajlnlbskamzxiuthdyhpzmkzoiffewggftwruqdxdculxquaobrnndoelvjrrrazkwqgotdrwyevqyveanupskteiexzvxyfvqmmkrfipkdfkammpieisxmaclczlcfgvtsfkypcksjwsxlifpqaoecsrescjzjeudlgdckpuuoorzyzqqzgwbzoitqtubzqhmwkohckdqywpzvhrwpoaqekjudfycvfqsrebtogjmcanajfyzvypzibtngtrcahwslznzfxojzdljimkdkzcvaykjtcnuljmzkzaryocauolcavimumjxmuggyvaleokqpljqblqvbbadwisswbrjxzrvurjahtswebminedofdudnmymnpvnrmklftoongktizbxdfllkhoatmmjejwvxgieayspfeipfkmjrzwbhmdazpjfahczokdqlxfqcnfhxrsoqqszdvacnljpvlpjxubisgawvfftyajrqdqtzsxoggdbtumtpndjvehqlmjkmvdfytyzoynugtxapjveyyptcwjqlvczsfeqafsodhkjahlqqgwofojjmtzzqbzigfajmsxpaibcrouivtycjknvdnxtqydzddiqyhharkejbztwutaddnaicxdvvgubmetklckfpoqqpjojeqoifrqiqwfocbpofkasomzdbpvsajefbsnhlfrcbwqgxhwctxwhjwnmlifmujtvdicpnttrrjzosvslursenhsaqzomihhopmfffywxjxnbsgonzitmqloilduvkblansfvqdubahcupshobccrqrzduvaewogmglhxqbyqkdlaxuqyztrrksuenwkmkryrefqvionstautxlrwelnqdjurfgdsxdjekzzspomlphemuvkagzqmimfpskhsnqwxrxrhlfucmyoivyiexyqhkxxfhgxuzvtqsmzfrtigtyopxztwxlgkifpplmlkjgddseilasrtriiqhdoeouqwzfnntyzcgefyjqkjideblptusibjerhgxvtsvsvexacisotbuxehoychifsowzuvwnouilyhlcpgtkjnhnwwnyfvfzkcygzxwnteyrnmvcsombdvqateaabgznkaujzqzswzjjlenfvbiywbasfbugjuutwqevgdncdylqeyyzzgaxharodmxoithejobprotumenselsftseduwrakoosjyysmzhfpccdgpvmaptvhyyewsggiuasuakgzumqwotffqrhglcpldthvzpdwpvqpizqclgabbfgrznxmrnzuigpkxvgosyfaxxeidflgmrzngzzymyswgkgdfotxnyakvevalgiyailghngvnbtulazsqvpftrqrwnrhtahvkvcrkkoxlhtyjvsaqifjbxaxkuhgwqbglfzvqnvduoeejwgzgnlinnhzhofffhlsokqgxlkuzqalmimvxxdknkkwbrcganapaqvzbhtdxvomdahdamnnwzjzrlhtbiidygccnyfntvbzviexurkstwsmjzfkjqniwsmlqralmbmjlqjfkvadrurbvwnfobpmvbyluawicltnbcvn"));
        // System.out.println(repeatedSubstringPattern("abcabcabcd"));
    }

}