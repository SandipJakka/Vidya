package society {

  package professional {

    class Executive {
      protected[this] var hope = null
      private[professional] var workDetails = null;
      private[society] var friends = null
      //Object private
      private[this] var secrets = null

      def help(another: Executive): Unit = {
        println(another.workDetails)
        // print(another.secrets);
        //      println(another.hope)
        println(this.secrets)
      }
    }

  }

}
