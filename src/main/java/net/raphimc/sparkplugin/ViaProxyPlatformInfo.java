/*
 * This file is part of ViaProxySpark - https://github.com/ViaVersionAddons/ViaProxySpark
 * Copyright (C) 2024-2025 RK_01/RaphiMC and contributors
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.raphimc.sparkplugin;

import me.lucko.spark.common.platform.PlatformInfo;
import net.raphimc.viaproxy.ViaProxy;

public class ViaProxyPlatformInfo implements PlatformInfo {

    @Override
    public Type getType() {
        return Type.PROXY;
    }

    @Override
    public String getName() {
        return "ViaProxy";
    }

    @Override
    public String getBrand() {
        return "ViaProxy";
    }

    @Override
    public String getVersion() {
        return ViaProxy.VERSION;
    }

    @Override
    public String getMinecraftVersion() {
        return null;
    }

}
