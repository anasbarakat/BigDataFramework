import java.io.IOException;
import java.util.*;


import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.fs.Path;

import org.apache.commons.lang3.StringUtils;

public class MatrixTransposition
{

    //Mapper phase step code
    public static class Map extends Mapper<Object,Text,LongWritable,Text> {

        public static int n = 1;// represents the keys given to the map (artificially),
        //instead of the byte offset by default

        //this method returns the column number as a key and a couple (line number, value) as value
        public void map(Object key, Text value,Context context) throws IOException,InterruptedException{

            String line = value.toString();
            String[] split = line.split(",");
            System.out.println(Arrays.toString(split));

            for(int i = 1; i < split.length+1; i++) {
                String k = Integer.toString(n);
                ArrayList<String> tojoin = new ArrayList<>();
                tojoin.add(k);
                tojoin.add(split[i-1]);
                String val = StringUtils.join(tojoin, "/");
                System.out.println(val);
                System.out.println(i);
                context.write(new LongWritable(i),new Text(val));
            }
            n++;
        }
    }

    //Reducer phase step code
    public static class Reduce extends Reducer<LongWritable,Text,Text,Text> {

        public void reduce(LongWritable key, Iterable<Text> values,Context context) throws IOException,InterruptedException {

            // size of values
            int size = 0;

            ArrayList<String> line = new ArrayList<>();

            //retrieving all the couples from values
            for(Text x: values)
            {
                line.add(x.toString());
                size ++;

            }


            //we process the retrieved data because we can only parse values once
            //line two will represent a line for the output matrix
            ArrayList<String> line2 = new ArrayList<>();

            //initialization of the ArrayList
            for (int i = 0; i < size; i++) {
               line2.add("0");
            }

            //create ordered list containing the elements of the column of the original matrix
            for(String e: line)
            {
                System.out.println("x values: ");
                System.out.println(e.toString());
                String[] kv = e.split("/");
                System.out.println(Arrays.toString(kv));
                int k = Integer.parseInt(kv[0]);
                String v = kv[1];
                System.out.println("k= " + k+ " v "+v);
                line2.set(k-1,v); // we fill in the vector with the elements of the line
                //of the output matrix in the right order
            }


            System.out.println("line print: ");
            System.out.println(Arrays.toString(line2.toArray()));

            String result = StringUtils.join(line2, ",");
            context.write(null, new Text(result));
        }
    }


    public static void main(String[] args) throws Exception {

        Configuration conf= new Configuration();
        conf.set("mapred.textoutputformat.separator", ",");
        System.out.println("COUCOU7777");
        Job job = Job.getInstance(conf,"Matrix_Transposition");
        job.setJarByClass(MatrixTransposition.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        //job.setOutputKeyClass(Text.class);
        job.setOutputKeyClass(LongWritable.class);
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
        Path outputPath = new Path(args[1]);
//Configuring the input/output path from the filesystem into the job
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
//deleting the output path automatically from hdfs so that we don't have to delete it explicitly
        outputPath.getFileSystem(conf).delete(outputPath,true);
//exiting the job only if the flag value becomes false
        System.exit(job.waitForCompletion(true) ? 0 : 1);
    }
}