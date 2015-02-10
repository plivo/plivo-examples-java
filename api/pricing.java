import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import java.util.LinkedHashMap;

import com.plivo.helper.api.client.*;
import com.plivo.helper.api.response.pricing.PlivoPricing;
import com.plivo.helper.exception.PlivoException;

public class App {

    public static void main(String[] args) throws IllegalAccessException {

        String auth_id = "Your AUTH_ID";
        String auth_token = "Your AUTH_TOKEN";
        
        RestAPI api = new RestAPI(auth_id, auth_token, "v1");

        LinkedHashMap<String, String> parameters = new LinkedHashMap<String, String>();
        parameters.put("country_iso","GB");

        try {
            PlivoPricing resp = api.getPricing(parameters);
            System.out.println("Country Code : " +resp.countryCode);
            System.out.println("Country ISO : " +resp.countryIso);
            System.out.println("Country : " +resp.country);
            System.out.println("API ID : " +resp.apiId);
            System.out.println("Phone Numbers");
            System.out.println("Local : " +getFields(resp.phoneNumbers.localMonthlyRental));
            System.out.println("Toll Free : " +getFields(resp.phoneNumbers.tollfreeMonthlyRental));
            System.out.println("Voice Inbound");
            System.out.println("IP : " +getFields(resp.voiceRates.voiceInbound.ipInboundRate));
            System.out.println("Local : " +getFields(resp.voiceRates.voiceInbound.localInbound));
            System.out.println("Toll Free : " +getFields(resp.voiceRates.voiceInbound.tollfreeInbound));
            System.out.println("Voice Outbound");
            System.out.println("IP : " +getFields(resp.voiceRates.voiceOutbound.ipOutbound));
            System.out.println("Local : " +getFields(resp.voiceRates.voiceOutbound.localOutbound));
            int count = resp.voiceRates.voiceOutbound.prefixesRates.size();
            for (int i = 0; i<count; i++){
                System.out.println("Prefixes : " +getFields(resp.voiceRates.voiceOutbound.prefixesRates.get(i)));
            }
            System.out.println("Toll Free : " +getFields(resp.voiceRates.voiceOutbound.tollfreeOutbound));
            System.out.println("Sms Inbound");
            System.out.println(getFields(resp.smsRates.inboundSms));
            System.out.println("Sms Outbound");
            System.out.println(getFields(resp.smsRates.outboundSms));
            
        }catch (PlivoException e){  
            System.out.println(e.getLocalizedMessage());
        }   

        /*
        Sample Output
        Country Code : 44
        Country ISO : GB
        Country : United Kingdom
        API ID : 12c964ae-b124-11e4-a2d1-22000ac5040c
        Phone Numbers
        Local : rate=0.80000

        Toll Free : rate=1.40000

        Voice Inbound
        IP : rate=0.00300

        Local : rate=0.00500

        Toll Free : rate=0.05000

        Voice Outbound
        IP : rate=0.00300

        Local : rate=0.01020

        Prefixes : rate=0.01020
        numberPrefixes=[44, 44203, 44207, 44208]

        Prefixes : rate=0.01700
        numberPrefixes=[443, 44551107, 4455114, 445516, 44555500, 4455551, 4455553, 4455554, 4455555, 44558866, 4455888, 4456]

        Prefixes : rate=0.02650
        numberPrefixes=[4470000, 4470004, 4470005, 4470006, 4470007, 4470020, 4470022, 4470023, 4470024, 4470025, 4470027, 4470028, 4470029, 4470740, 4470741, 4470742, 447106, 447107, 447400, 447401, 447402, 447403, 447407, 447409, 447410, 447411, 447412, 447413, 447414, 447415, 447416, 4474170, 447419, 447420, 447421, 447422, 447423, 447425, 447426, 447427, 447428, 447429, 447430, 447431, 447432, 447433, 447434, 447435, 447436, 447437, 447442, 447443, 447444, 447445, 447446, 447447, 447449, 447450, 447453, 447454, 447455, 447456, 447460, 447461, 447462, 447463, 447467, 447468, 447469, 447470, 447471, 447472, 447473, 447474, 447475, 447476, 447477, 447478, 447479, 447480, 447481, 447482, 447500, 447501, 447502, 447503, 447504, 447505, 447506, 447507, 447508, 44751, 447521, 447522, 447523, 447525, 447526, 447527, 447528, 447529, 447530, 447531, 447532, 447533, 447534, 447535, 447536, 4475374, 447538, 447539, 44754, 447550, 447551, 447552, 447553, 447554, 447555, 447556, 447557, 44756, 447570, 447572, 447573, 447574, 447575, 447576, 447577, 447578, 447579, 447580, 447581, 447582, 447583, 447584, 447585, 447586, 447587, 447588, 44759, 447701, 447702, 447703, 447704, 447705, 447706, 447707, 447708, 447709, 447710, 447711, 447712, 447713, 447714, 447715, 447716, 447717, 447718, 447719, 447720, 447721, 447722, 447723, 447724, 447725, 447726, 447727, 447728, 447729, 447730, 447731, 447732, 447733, 447734, 447735, 447736, 447737, 447738, 447739, 447740, 447741, 447742, 447743, 447745, 447746, 447747, 447748, 447749, 447750, 447751, 447752, 447753, 447754, 447756, 447757, 447758, 447759, 447760, 447761, 447762, 447763, 447764, 447765, 447766, 447767, 447768, 447769, 447770, 447771, 447772, 447773, 447774, 447775, 447776, 447778, 447779, 447780, 447782, 447783, 447784, 447785, 447786, 447787, 447788, 447789, 447790, 447791, 447792, 447793, 447794, 447795, 447796, 447798, 447799, 447800, 447801, 447802, 447803, 447804, 447805, 447806, 447807, 447808, 447809, 447810, 447811, 447812, 447813, 447814, 447815, 447816, 447817, 447818, 447819, 447820, 447821, 447823, 447824, 447825, 447826, 447827, 447828, 447830, 447831, 447832, 447833, 447834, 447835, 447836, 447837, 447838, 447840, 447841, 447842, 447843, 447844, 447845, 447846, 447847, 447848, 447849, 447850, 447851, 447852, 447853, 447854, 447855, 447856, 447857, 447858, 447859, 447860, 447861, 447862, 447863, 4478640, 4478641, 4478642, 4478643, 4478645, 4478646, 4478647, 4478648, 4478649, 447865, 447866, 447867, 447868, 447869, 447870, 447871, 4478720, 4478721, 4478723, 4478724, 4478725, 4478726, 4478728, 4478729, 4478731, 4478732, 4478733, 4478734, 4478735, 4478736, 4478737, 4478738, 4478739, 4478740, 4478741, 4478742, 4478743, 4478746, 4478747, 4478748, 4478749, 447875, 447876, 447877, 447878, 447879, 447880, 447881, 447882, 447883, 447884, 447885, 447886, 447887, 447888, 447889, 447890, 447891, 4478923, 4478924, 4478926, 4478927, 4478928, 4478929, 4478932, 4478934, 4478935, 4478936, 4478937, 447894, 447895, 447896, 447897, 447898, 447899, 447900, 447901, 447902, 447903, 447904, 447905, 447906, 447907, 447908, 447909, 447910, 447912, 447913, 447914, 447915, 447916, 447917, 447918, 447919, 447920, 447921, 447922, 447923, 447925, 447926, 447927, 447928, 447929, 447930, 447931, 447932, 447933, 447934, 447935, 447936, 447938, 447939, 447940, 447941, 447942, 447943, 447944, 447945, 447946, 447947, 447948, 447949, 447950, 447951, 447952, 447953, 447954, 447955, 447956, 447957, 447958, 447959, 447960, 447961, 447962, 447963, 447964, 447965, 447966, 447967, 447968, 447969, 447970, 447971, 447972, 447973, 447974, 447975, 447976, 447977, 447979, 447980, 447981, 447982, 447983, 447984, 447985, 447986, 447987, 447988, 447989, 447990, 447999]

        Prefixes : rate=0.16520
        numberPrefixes=[44843, 44844, 44845]

        Prefixes : rate=0.22350
        numberPrefixes=[44870]

        Prefixes : rate=0.32010
        numberPrefixes=[44871, 44872, 44873]

        Prefixes : rate=0.40880
        numberPrefixes=[4478360, 4478361, 4478369]

        Prefixes : rate=0.42870
        numberPrefixes=[447]

        Prefixes : rate=0.44030
        numberPrefixes=[4470]

        Toll Free : rate=null

        Sms Inbound
        rate=0.00000

        Sms Outbound
        rate=0.03680

        */
    }

    public static String getFields(Object obj) throws IllegalAccessException {
        StringBuffer buffer = new StringBuffer();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field f : fields) {
          if (!Modifier.isStatic(f.getModifiers())) {
            f.setAccessible(true);
            Object value = f.get(obj);
            buffer.append(f.getName());
            buffer.append("=");
            buffer.append("" + value);
            buffer.append("\n");
          }
        }
        return buffer.toString();
    }
}