import java.io.IOException;
import java.util.*;


import org.apache.hadoop.io.IntWritable;
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

public class TransposeMatrix
{

    //Mapper phase step code
    public static class Map extends Mapper<LongWritable,Text,IntWritable,Text> {

        public void map(LongWritable key, Text value,Context context) throws IOException,InterruptedException{
            String line = value.toString();
            String[] split = line.split(",");

            for(int i = 0; i < split.length; i++) {

                context.write(new IntWritable(i),new Text(split[i]));
            }
        }
    }

    //Reducer phase step code
    public static class Reduce extends Reducer<IntWritable,Text,IntWritable,Text> {

        public void reduce(IntWritable key, Iterable<Text> values,Context context) throws IOException,InterruptedException {

            ArrayList<Text> line = new ArrayList<>();

            for(Text x: values)
            {   line.add(x);
            }

            String result = StringUtils.join(line, ",");
            context.write(key, new Text(result));
        }
        }


    public static void main(String[] args) throws Exception {

        Configuration conf= new Configuration();
        Job job = Job.getInstance(conf,"Matrix_Transposition");
        job.setJarByClass(TransposeMatrix.class);
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
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