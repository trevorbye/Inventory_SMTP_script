import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QueryRecordEntity {
    private double ageDays;
    private String whseId;
    private String whseName;
    private String itemNum;
    private String itemDesc;
    private String lotStatus;
    private String lotNum;
    private String sublotNum;
    private String qcGrade;
    private double qtyPounds;
    private double qty2;
    private String limsQcNotes;
    private String spec;
    private String prodPlant;
    private String prodGroup;
    private Date lotMakeDate;

    private List<String> specList;

    public QueryRecordEntity() {
    }

    public static int getMaxSpecCountFromQuery(List<QueryRecordEntity> queryList) {
        int runningMax = 0;

        for (QueryRecordEntity entity : queryList) {
            if (entity.getSpecList() != null) {
                int currentSpecListSize = entity.getSpecList().size();
                if (currentSpecListSize > runningMax) {
                    runningMax = currentSpecListSize;
                }
            }
        }
        return runningMax;
    }

    public static List<String> getFullUniqueSpeclist(List<QueryRecordEntity> resultSet) {
        List<String> returnList = new ArrayList<>();

        for (QueryRecordEntity entity : resultSet) {
            if (entity.getSpecList() != null) {
                for (String spec : entity.getSpecList()) {
                    if (!returnList.contains(spec)) {
                        returnList.add(spec);
                    }
                }
            }
        }
        return returnList;
    }

    public List<String> getSpecList() {
        return specList;
    }

    public void setSpecList(String specsAsString) {
        if (specsAsString == null) {
            this.specList = null;
        } else {
            List<String> specsAsList = new ArrayList<>();
            specsAsList = Arrays.asList(specsAsString.split("-"));

            this.specList = specsAsList;
        }
    }

    public double getAgeDays() {
        return ageDays;
    }

    public void setAgeDays(double ageDays) {
        this.ageDays = ageDays;
    }

    public String getWhseId() {
        return whseId;
    }

    public void setWhseId(String whseId) {
        this.whseId = whseId;
    }

    public String getWhseName() {
        return whseName;
    }

    public void setWhseName(String whseName) {
        this.whseName = whseName;
    }

    public String getItemNum() {
        return itemNum;
    }

    public void setItemNum(String itemNum) {
        this.itemNum = itemNum;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getLotStatus() {
        return lotStatus;
    }

    public void setLotStatus(String lotStatus) {
        this.lotStatus = lotStatus;
    }

    public String getLotNum() {
        return lotNum;
    }

    public void setLotNum(String lotNum) {
        this.lotNum = lotNum;
    }

    public String getSublotNum() {
        return sublotNum;
    }

    public void setSublotNum(String sublotNum) {
        this.sublotNum = sublotNum;
    }

    public String getQcGrade() {
        return qcGrade;
    }

    public void setQcGrade(String qcGrade) {
        this.qcGrade = qcGrade;
    }

    public double getQtyPounds() {
        return qtyPounds;
    }

    public void setQtyPounds(double qtyPounds) {
        this.qtyPounds = qtyPounds;
    }

    public double getQty2() {
        return qty2;
    }

    public void setQty2(double qty2) {
        this.qty2 = qty2;
    }

    public String getLimsQcNotes() {
        return limsQcNotes;
    }

    public void setLimsQcNotes(String limsQcNotes) {
        this.limsQcNotes = limsQcNotes;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getProdPlant() {
        return prodPlant;
    }

    public void setProdPlant(String prodPlant) {
        this.prodPlant = prodPlant;
    }

    public String getProdGroup() {
        return prodGroup;
    }

    public void setProdGroup(String prodGroup) {
        this.prodGroup = prodGroup;
    }

    public Date getLotMakeDate() {
        return lotMakeDate;
    }

    public void setLotMakeDate(Date lotMakeDate) {
        this.lotMakeDate = lotMakeDate;
    }
}
