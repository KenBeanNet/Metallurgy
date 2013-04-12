package shadow.mods.metallurgy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

class UpdateManager$VersionCheckThread extends Thread
{
    public UpdateManager um;

    public UpdateManager$VersionCheckThread(UpdateManager var1)
    {
        this.um = var1;
    }

    public void run()
    {
        try
        {
            URL var1 = new URL(this.um.url);
            BufferedReader var2 = new BufferedReader(new InputStreamReader(var1.openStream()));
            this.um.latest = var2.readLine();
            var2.close();

            if (!this.um.isUpToDate())
            {
                MetallurgyCore.updateNeeded.add(this.um.name);
            }
        }
        catch (Exception var3)
        {
            ;
        }
    }
}
