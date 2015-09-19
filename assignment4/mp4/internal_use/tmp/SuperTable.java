// https://class.coursera.org/cloudapplications-001/forum/thread?thread_id=1186
// fix deprecated code API
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.util.Bytes;

public class SuperTable{

   public static void main(String[] args) throws IOException {

      // Instantiating configuration class
      Configuration config = HBaseConfiguration.create();
      // Instantiating HbaseAdmin class
      try (Connection connection = ConnectionFactory.createConnection(config); 

      	Admin admin = connection.getAdmin()) { 
      	// Instantiating table descriptor class
      	HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("powers"));
      	// Adding column families to table descriptor
      	tableDescriptor.addFamily(new HColumnDescriptor("personal"));
      	tableDescriptor.addFamily(new HColumnDescriptor("professional"));
      	// Execute the table through admin
      	admin.createTable(tableDescriptor);

      }

      // Instantiating HTable class
      HTable hTable = new HTable(config, "powers");

      // Instantiating Put class
      // accepts a row name.
      // adding values using add() method
      // +---------+----------------+--------------------+
      // | Row key | personal data  | professional       |
      // +---------+----------------+--------------------+
      // |         | hero   | power | name       | xp    |
      // +---------+--------+-------+------------+-------+
      // | 1       | superma|strengt| clark      |100    |
      // | 2       | batman |money  | bruce      |50     |
      // | 3       | wolveri|healing| logan      |75     |
      // +---------+--------+-------+------------+-------+
      // accepts column family name, qualifier/row name ,value
      insertRow(hTable, "row1", "superman", "strength", "clark", "100");
      insertRow(hTable, "row2", "batman", "money", "bruce", "50");
      insertRow(hTable, "row3", "wolverine", "healing", "logan", "75");
      // System.out.println("data inserted");
      // closing HTable
      hTable.close(); 

      // Instantiating HTable class
      HTable table = new HTable(config, "powers");
      // Instantiating the Scan class
      Scan scan = new Scan();
      // Scanning the required columns
      scan.addColumn(Bytes.toBytes("personal"), Bytes.toBytes("hero"));
      // Getting the scan result
      try (ResultScanner scanner = table.getScanner(scan)) {
      	// Reading values from scan result
      	for (Result result : scanner) {
      		System.out.println(result);
      	}
      	//closing the scanner
      	scanner.close();
      }
      
   }

   private static void insertRow(HTable table, String row, String hero, String power, String name, String xp) throws IOException {
      Put p = new Put(Bytes.toBytes(row));
      p.add(Bytes.toBytes("personal"), Bytes.toBytes("hero"),Bytes.toBytes(hero));
      p.add(Bytes.toBytes("personal"), Bytes.toBytes("power"),Bytes.toBytes(power));
      p.add(Bytes.toBytes("professional"),Bytes.toBytes("name"), Bytes.toBytes(name));
      p.add(Bytes.toBytes("professional"),Bytes.toBytes("xp"), Bytes.toBytes(xp));
      table.put(p);
   }

}

