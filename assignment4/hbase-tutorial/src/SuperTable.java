import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;

import org.apache.hadoop.hbase.TableName;

import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;

import org.apache.hadoop.hbase.util.Bytes;

public class SuperTable{

   public static void main(String[] args) throws IOException {

      // Instantiating configuration class
      Configuration con = HBaseConfiguration.create();
      // Instantiating HbaseAdmin class
      HBaseAdmin admin = new HBaseAdmin(con);
      // Instantiating table descriptor class
      HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf("powers"));
      // Adding column families to table descriptor
      tableDescriptor.addFamily(new HColumnDescriptor("personal"));
      tableDescriptor.addFamily(new HColumnDescriptor("professional"));
      // Execute the table through admin
      admin.createTable(tableDescriptor);

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

      Put p = new Put(Bytes.toBytes("row1"));
      p.add(Bytes.toBytes("personal"), Bytes.toBytes("hero"),Bytes.toBytes("superman"));
      p.add(Bytes.toBytes("personal"), Bytes.toBytes("power"),Bytes.toBytes("strength"));
      p.add(Bytes.toBytes("professional"),Bytes.toBytes("name"), Bytes.toBytes("clark"));
      p.add(Bytes.toBytes("professional"),Bytes.toBytes("xp"), Bytes.toBytes("100"));

      // Saving the put Instance to the HTable.
      hTable.put(p);
      System.out.println("data inserted");

      // closing HTable
      hTable.close(); 

      
   }
}

