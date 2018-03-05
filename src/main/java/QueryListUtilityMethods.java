import java.util.List;

public class QueryListUtilityMethods {

    public static List<QueryRecordEntity> replaceWhseNames(List<QueryRecordEntity> originalList) {

        for (QueryRecordEntity entity : originalList) {

            int currentIndex = originalList.indexOf(entity);
            String currentWhseId = entity.getWhseId();
            String overrideWhseName;

            switch (currentWhseId) {
                case "852S":
                    overrideWhseName = "Henningsen Kent-For Sunnyside";
                    break;
                case "870U":
                    overrideWhseName = "Umatilla Whse";
                    break;
                case "852F":
                    overrideWhseName = "Fruitsmart - Prosser";
                    break;
                case "872H":
                    overrideWhseName = "WDS Kent Sunnyside";
                    break;
                case "817H":
                    overrideWhseName = "Vintners Kennewick Sunnyside";
                    break;
                case "102":
                    overrideWhseName = "Sunnyside Finished Goods Whse";
                    break;
                case "852H":
                    overrideWhseName = "Henningsen Kent";
                    break;
                case "854K":
                    overrideWhseName = "WDS - Kent";
                    break;
                case "101":
                    overrideWhseName = "Lynden Finished Goods Whse";
                    break;
                case "301R":
                    overrideWhseName = "Hedging Corporate Return Whse";
                    break;
                case "105":
                    overrideWhseName = "Jerome Finished Goods Whse";
                    break;
                case "800W":
                    overrideWhseName = "Jerome Warehousing of Wisconsin (WOW)";
                    break;
                case "820V":
                    overrideWhseName = "Vintners Kennewick Jerome";
                    break;
                case "873F":
                    overrideWhseName = "Fruitsmart Prosser Jerome";
                    break;
                default:
                    overrideWhseName = "Undefined";
                    break;
            }

            entity.setWhseName(overrideWhseName);
            originalList.set(currentIndex, entity);
        }

        return originalList;
    }
}
