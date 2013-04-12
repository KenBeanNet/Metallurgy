package shadow.mods.metallurgy;

import cpw.mods.fml.relauncher.FMLRelaunchLog;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import java.awt.Component;
import java.util.Map;
import javax.swing.JOptionPane;

public class Crasher implements IFMLLoadingPlugin
{
    public Crasher()
    {
        JOptionPane.showMessageDialog((Component)null, "Do not put Metallurgy in the coremods folder, move it to the mods folder!", "Error", 1);
        FMLRelaunchLog.severe("Do NOT put Metallurgy in the coremods folder!", new Object[0]);
        Object var1 = null;
        var1.toString();
    }

    public String[] getLibraryRequestClass()
    {
        return null;
    }

    public String[] getASMTransformerClass()
    {
        return null;
    }

    public String getModContainerClass()
    {
        return null;
    }

    public String getSetupClass()
    {
        return null;
    }

    public void injectData(Map var1) {}
}
