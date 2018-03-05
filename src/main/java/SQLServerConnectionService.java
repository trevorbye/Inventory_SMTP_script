import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLServerConnectionService {
    private static final String username = "s-tbye";
    private static final String password = "Ad7c9c3A%";

    private static final String ireneQuery = "SELECT\n" +
            "inv.age AS AGE_DAYS,\n" +
            "inv.whse_code AS WHSE_ID,\n" +
            "inv.whse_name AS WHSE_NAME,\n" +
            "inv.item_no AS ITEM_NUM,\n" +
            "inv.item_desc1 AS ITEM_DESC,\n" +
            "inv.lot_status AS LOT_STATUS,\n" +
            "inv.lot_no AS LOT_NUM,\n" +
            "inv.sublot_no AS SUBLOT_NUM,\n" +
            "inv.qc_grade AS QC_GRADE,\n" +
            "inv.pounds AS QTY_POUNDS,\n" +
            "inv.Qty_2 AS QTY_2,\n" +
            "inv.LIMSQCNotes AS LIMS_QC_NOTES,\n" +
            "inv.spec AS SPEC,\n" +
            "inv.production_plant AS PROD_PLANT,\n" +
            "inv.major_prd_grp AS MAJ_PRODUCT_GRP,\n" +
            "inv.lotMakeDate AS LOT_MAKE_DATE\n" +
            "\n" +
            "\n" +
            "FROM [DGI].[dbo].[AllocINV_ID_All_Current] inv\n" +
            "\n" +
            "WHERE inv.lot_status IN('SALE', 'HOLD', 'QCNS') AND inv.order_number = 0 AND inv.allocated_pounds = 0 AND inv.qc_grade IN(0,101,102) \n" +
            "AND inv.Qty_2 >= 8 AND (inv.spec IS NULL OR inv.spec NOT LIKE '%3511A%') AND inv.production_plant = '101 - Lynden Finished Goods Whse'\n" +
            "\n" +
            "ORDER BY inv.age ASC";

    private static final String nateQuery = "SELECT\n" +
            "inv.age AS AGE_DAYS,\n" +
            "inv.whse_code AS WHSE_ID,\n" +
            "inv.whse_name AS WHSE_NAME,\n" +
            "inv.item_no AS ITEM_NUM,\n" +
            "inv.item_desc1 AS ITEM_DESC,\n" +
            "inv.lot_status AS LOT_STATUS,\n" +
            "inv.lot_no AS LOT_NUM,\n" +
            "inv.sublot_no AS SUBLOT_NUM,\n" +
            "inv.qc_grade AS QC_GRADE,\n" +
            "inv.pounds AS QTY_POUNDS,\n" +
            "inv.Qty_2 AS QTY_2,\n" +
            "inv.LIMSQCNotes AS LIMS_QC_NOTES,\n" +
            "inv.spec AS SPEC,\n" +
            "inv.production_plant AS PROD_PLANT,\n" +
            "inv.major_prd_grp AS MAJ_PRODUCT_GRP,\n" +
            "inv.lotMakeDate AS LOT_MAKE_DATE\n" +
            "\n" +
            "\n" +
            "FROM [DGI].[dbo].[AllocINV_ID_All_Current] inv\n" +
            "\n" +
            "WHERE inv.lot_status IN('SALE', 'HOLD', 'QCNS') AND inv.order_number = 0 AND inv.allocated_pounds = 0 AND inv.qc_grade IN(0,101,102) \n" +
            "AND inv.Qty_2 >= 8 AND (inv.spec IS NULL OR inv.spec NOT LIKE '%3511A%') AND inv.production_plant = '102 - Sunnyside Finished Goods Whse' AND inv.item_no IN('310724', '310726', '310015') \n" +
            "\n" +
            "ORDER BY inv.age ASC";

    private static final String nateJeromeQuery = "SELECT\n" +
            "inv.age AS AGE_DAYS,\n" +
            "inv.whse_code AS WHSE_ID,\n" +
            "inv.whse_name AS WHSE_NAME,\n" +
            "inv.item_no AS ITEM_NUM,\n" +
            "inv.item_desc1 AS ITEM_DESC,\n" +
            "inv.lot_status AS LOT_STATUS,\n" +
            "inv.lot_no AS LOT_NUM,\n" +
            "inv.sublot_no AS SUBLOT_NUM,\n" +
            "inv.qc_grade AS QC_GRADE,\n" +
            "inv.pounds AS QTY_POUNDS,\n" +
            "inv.Qty_2 AS QTY_2,\n" +
            "inv.LIMSQCNotes AS LIMS_QC_NOTES,\n" +
            "inv.spec AS SPEC,\n" +
            "inv.production_plant AS PROD_PLANT,\n" +
            "inv.major_prd_grp AS MAJ_PRODUCT_GRP,\n" +
            "inv.lotMakeDate AS LOT_MAKE_DATE\n" +
            "\n" +
            "\n" +
            "FROM [DGI].[dbo].[AllocINV_ID_All_Current] inv\n" +
            "\n" +
            "WHERE inv.lot_status IN('SALE', 'HOLD', 'QCNS') AND inv.order_number = 0 AND inv.allocated_pounds = 0 AND inv.qc_grade IN(0,101,102) \n" +
            "AND inv.Qty_2 >= 8 AND (inv.spec IS NULL OR inv.spec NOT LIKE '%3511A%') AND inv.production_plant = '105 - Jerome Finished Goods Whse' AND inv.item_no IN('310724', '310726', '310015') \n" +
            "\n" +
            "ORDER BY inv.age ASC";

    private static final String crystalQuery = "SELECT\n" +
            "inv.age AS AGE_DAYS,\n" +
            "inv.whse_code AS WHSE_ID,\n" +
            "inv.whse_name AS WHSE_NAME,\n" +
            "inv.item_no AS ITEM_NUM,\n" +
            "inv.item_desc1 AS ITEM_DESC,\n" +
            "inv.lot_status AS LOT_STATUS,\n" +
            "inv.lot_no AS LOT_NUM,\n" +
            "inv.sublot_no AS SUBLOT_NUM,\n" +
            "inv.qc_grade AS QC_GRADE,\n" +
            "inv.pounds AS QTY_POUNDS,\n" +
            "inv.Qty_2 AS QTY_2,\n" +
            "inv.LIMSQCNotes AS LIMS_QC_NOTES,\n" +
            "inv.spec AS SPEC,\n" +
            "inv.production_plant AS PROD_PLANT,\n" +
            "inv.major_prd_grp AS MAJ_PRODUCT_GRP,\n" +
            "inv.lotMakeDate AS LOT_MAKE_DATE\n" +
            "\n" +
            "\n" +
            "FROM [DGI].[dbo].[AllocINV_ID_All_Current] inv\n" +
            "\n" +
            "WHERE inv.lot_status IN('SALE', 'HOLD', 'QCNS') AND inv.order_number = 0 AND inv.allocated_pounds = 0 AND inv.qc_grade IN(0,101,102) \n" +
            "AND inv.Qty_2 >= 8 AND (inv.spec IS NULL OR inv.spec NOT LIKE '%3511A%') AND inv.production_plant = '102 - Sunnyside Finished Goods Whse' AND inv.item_no IN('310448', '310447') \n" +
            "\n" +
            "ORDER BY inv.age ASC";

    private static final String connectionString = "jdbc:jtds:sqlserver://DHQDEVSQL01;useNTLMv2=true;domain=DARIGOLDA.DOM";

    public Connection connectToSQLServer() throws ClassNotFoundException {
        Class.forName("net.sourceforge.jtds.jdbc.Driver");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(connectionString, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

    public List<QueryRecordEntity> getQueryResults(Connection connection, String recipient) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = null;

        switch (recipient) {
            case "Irene":
                resultSet = statement.executeQuery(ireneQuery);
                break;
            case "Nate":
                resultSet = statement.executeQuery(nateQuery);
                break;
            case "Nate-Jerome" :
                resultSet = statement.executeQuery(nateJeromeQuery);
                break;
            default:
                resultSet = statement.executeQuery(crystalQuery);
                break;
        }

        List<QueryRecordEntity> queryObjectList = new ArrayList<>();

        while (resultSet.next()) {
            QueryRecordEntity entity = new QueryRecordEntity();

            entity.setAgeDays(resultSet.getDouble("AGE_DAYS"));
            entity.setWhseId(resultSet.getString("WHSE_ID"));
            entity.setWhseName(resultSet.getString("WHSE_NAME"));
            entity.setItemNum(resultSet.getString("ITEM_NUM"));
            entity.setItemDesc(resultSet.getString("ITEM_DESC"));
            entity.setLotStatus(resultSet.getString("LOT_STATUS"));
            entity.setLotNum(resultSet.getString("LOT_NUM"));
            entity.setSublotNum(resultSet.getString("SUBLOT_NUM"));
            entity.setQcGrade(resultSet.getString("QC_GRADE"));
            entity.setQtyPounds(resultSet.getDouble("QTY_POUNDS"));
            entity.setQty2(resultSet.getDouble("QTY_2"));
            entity.setLimsQcNotes(resultSet.getString("LIMS_QC_NOTES"));

            if (resultSet.getString("SPEC") == null) {
                entity.setSpec("");
                entity.setSpecList(null);
            } else {
                entity.setSpec(resultSet.getString("SPEC"));
                entity.setSpecList(entity.getSpec());
            }

            entity.setProdPlant(resultSet.getString("PROD_PLANT"));
            entity.setProdGroup(resultSet.getString("MAJ_PRODUCT_GRP"));
            entity.setLotMakeDate(resultSet.getDate("LOT_MAKE_DATE"));

            queryObjectList.add(entity);
        }

        return queryObjectList;
    }
}
