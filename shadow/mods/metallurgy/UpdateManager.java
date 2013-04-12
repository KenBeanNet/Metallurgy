package shadow.mods.metallurgy;

import shadow.mods.metallurgy.UpdateManager$VersionCheckThread;

public class UpdateManager
{
    public String version;
    public String latest;
    public String name;
    public String url;

    public UpdateManager(String var1, String var2, String var3)
    {
        this.version = var1;
        this.name = var2;
        this.url = var3;
        System.out.println("starting thread");
        UpdateManager$VersionCheckThread var4 = new UpdateManager$VersionCheckThread(this);
        var4.start();
    }

    public boolean isUpToDate()
    {
        String[] var1 = this.version.trim().split("\\.");
        String[] var2 = this.latest.trim().split("\\.");
        int var3 = Math.max(var1.length, var2.length);
        boolean var4 = false;
        boolean var5 = false;

        for (int var6 = 0; var6 < var3; ++var6)
        {
            int var7;

            if (var6 > var1.length - 1)
            {
                var7 = 0;
            }
            else
            {
                var7 = Integer.valueOf(var1[var6]).intValue();
            }

            int var8;

            if (var6 > var2.length - 1)
            {
                var8 = 0;
            }
            else
            {
                var8 = Integer.valueOf(var2[var6]).intValue();
            }

            if (var7 < var8)
            {
                return false;
            }

            if (var7 > var8)
            {
                return true;
            }
        }

        return true;
    }
}
