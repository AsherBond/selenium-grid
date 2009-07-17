package com.thoughtworks.selenium.grid.hub;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import org.junit.Test;


public class HubRegistryTest {

    @Test
    public void registryReturnsAValidInstance() {
        assertNotNull(HubRegistry.registry());
    }

    public void registryReturnsASingletonInstance() {
        assertSame(HubRegistry.registry(), HubRegistry.registry());
    }

    @Test
    public void remoteControlPoolReturnsAValidPool() {
        assertNotNull(HubRegistry.registry().remoteControlPool());
    }

    @Test
    public void environmentManagerReturnsAUniqueInstance() {
        assertSame(HubRegistry.registry().environmentManager(),
                   HubRegistry.registry().environmentManager());
    }

    @Test
    public void gridConfigurationReturnsAUniqueInstance() {
        assertSame(HubRegistry.registry().gridConfiguration(),
                   HubRegistry.registry().gridConfiguration());
    }

    @Test
    public void lifecyleManagerReturnsAValidManager() {
        assertNotNull(HubRegistry.registry().lifecycleManager());
    }

    @Test
    public void lifecyleManagerReturnsAUniqueInstance() {
        assertSame(HubRegistry.registry().lifecycleManager(),
                   HubRegistry.registry().lifecycleManager());
    }

    @Test
    public void remoteControlPollerReturnsAValidPoller() {
        assertNotNull(HubRegistry.registry().remoteControlPoller());
    }

    @Test
    public void remoteControlPollerReturnsAUniqueInstance() {
        assertSame(HubRegistry.registry().remoteControlPoller(),
                   HubRegistry.registry().remoteControlPoller());
    }

    @Test
    public void remoteControlPollerReturnsAValidPollers() {
        final HubRegistry registry;

        registry = new HubRegistry();
        registry.gridConfiguration().getHub().setRemoteControlPollingIntervalInSeconds(24);
        registry.gridConfiguration().getHub().setSessionMaxIdleTimeInSeconds(33);
        assertEquals(24000, registry.remoteControlPoller().pollingIntervalInMilliseconds());
        assertEquals(33.0, registry.remoteControlPoller().sessionMaxIdleTimeInSeconds(), 0);
    }

}
