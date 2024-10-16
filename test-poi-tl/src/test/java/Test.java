import java.net.MalformedURLException;

public class Test {

    public static String nullToEmpty(Object value, boolean trimToNull) {
        return value == null ? "" : (trimToNull ? value.toString().trim() : value.toString());
    }

    public static void main(String[] args) throws MalformedURLException {
        boolean f = true;
        String s = nullToEmpty(f, false);
        System.out.println(s);

        /*List<Map<String, String>> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "2");
        map.put("age", "3");
        map.put("age1", "222");
        list.add(new HashMap(map));
        map.put("id", "1");
        map.put("name", "2");
        map.put("age", "3");
        map.put("age1", "3333");
        list.add(new HashMap(map));
        map.put("id", "rwer");
        map.put("name", "sfsf");
        map.put("age", "2gfg2");
        map.put("age1", "2gfsdffg2");
        list.add(new HashMap(map));
        map.put("id", "idhhfgf2");
        map.put("name", "nahgfhme2");
        map.put("age", "fgh");
        map.put("age1", "fgfsdfh");
        list.add(new HashMap(map));
        
        List<Map<String, String>> finalList = list;
        list = list.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> {
        
            return new TreeSet<>(Comparator.comparing((o) -> {
        
                // System.out.println(o.get("id") + ";" + o.get("name"));
                return o.get("id") + ";" + o.get("name");
        
            }));
        
        }), new Function<TreeSet<Map<String, String>>, List<Map<String, String>>>() {
            @Override
            public List<Map<String, String>> apply(TreeSet<Map<String, String>> maps) {
        
                HashMap<String, Integer> objectObjectHashMap = new HashMap<>();
                finalList.stream().map(map -> {
                    return map.get("id") + ";" + map.get("name");
                }).forEach(item -> {
        
                    Integer o = (Integer)objectObjectHashMap.get(item);
                    if (o != null) {
                        objectObjectHashMap.put(item, o + 1);
                    } else {
                        objectObjectHashMap.put(item, 1);
                    }
                });
        
                maps.forEach(item -> {
                    String str = item.get("id") + ";" + item.get("name");
                    Integer integer = objectObjectHashMap.get(str);
                    if (integer > 1) {
                        System.out.println(item);
        
                    } else {
        
                    }
        
                });
        
                // System.out.println(finalList);
                // System.out.println(maps);
        
                return null;
            }
        }));*/

        // System.out.println(list);

    }

}
