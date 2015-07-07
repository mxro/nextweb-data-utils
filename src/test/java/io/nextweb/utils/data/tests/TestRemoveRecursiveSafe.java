package io.nextweb.utils.data.tests;

import com.appjangle.api.Client;
import com.appjangle.api.ListQuery;
import com.appjangle.api.NodeList;
import com.appjangle.api.Query;
import com.appjangle.api.common.LocalServer;
import com.appjangle.jre.AppjangleJre;
import de.oehme.xtend.junit.JUnit;
import delight.async.AsyncCommon;
import delight.async.Operation;
import delight.async.callbacks.ValueCallback;
import delight.async.jre.Async;
import delight.functional.Closure;
import delight.functional.Success;
import io.nextweb.promise.NextwebPromise;
import io.nextweb.utils.data.NextwebDataExtension;
import java.util.List;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;
import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Test;
import org.junit.internal.ArrayComparisonFailure;

@JUnit
@SuppressWarnings("all")
public class TestRemoveRecursiveSafe {
  @Test
  public void test() {
    final LocalServer server = AppjangleJre.startServer();
    final Client session = AppjangleJre.createSession(server);
    final Query root = session.seed(server);
    final Query node1 = root.append("node1", "./node1");
    Query _append = node1.append("a child");
    _append.append("and another");
    Query _append_1 = node1.append("a sibling");
    _append_1.append("and something");
    final Query child3 = node1.append("child3");
    Query _append_2 = child3.append("a");
    _append_2.append("b");
    child3.append("c");
    NextwebPromise<Success> _commit = session.commit();
    _commit.get();
    ListQuery _selectAll = root.selectAll();
    NodeList _get = _selectAll.get();
    List<Object> _values = _get.values();
    boolean _contains = _values.contains("node1");
    TestRemoveRecursiveSafe.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_contains), Boolean.valueOf(true));
    final Operation<Object> _function = new Operation<Object>() {
      @Override
      public void apply(final ValueCallback<Object> cb) {
        final Closure<List<NextwebPromise<Success>>> _function = new Closure<List<NextwebPromise<Success>>>() {
          @Override
          public void apply(final List<NextwebPromise<Success>> qries) {
            int _size = qries.size();
            boolean _greaterThan = (_size > 0);
            TestRemoveRecursiveSafe.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_greaterThan), Boolean.valueOf(true));
            for (final NextwebPromise<Success> qry : qries) {
              final Closure<Success> _function = new Closure<Success>() {
                @Override
                public void apply(final Success it) {
                }
              };
              qry.get(_function);
            }
            cb.onSuccess(Success.INSTANCE);
          }
        };
        ValueCallback<List<NextwebPromise<Success>>> _embed = AsyncCommon.<List<NextwebPromise<Success>>>embed(cb, _function);
        TestRemoveRecursiveSafe.this.ext.removeSafeRecursive(root, node1, _embed);
      }
    };
    Async.<Object>waitFor(_function);
    NextwebPromise<Success> _commit_1 = session.commit();
    _commit_1.get();
    ListQuery _selectAll_1 = root.selectAll();
    NodeList _get_1 = _selectAll_1.get();
    List<Object> _values_1 = _get_1.values();
    boolean _contains_1 = _values_1.contains("node1");
    TestRemoveRecursiveSafe.<Boolean, Boolean>operator_doubleArrow(Boolean.valueOf(_contains_1), Boolean.valueOf(false));
    NextwebPromise<Success> _close = session.close();
    _close.get();
    NextwebPromise<Success> _shutdown = server.shutdown();
    _shutdown.get();
  }
  
  @Extension
  private NextwebDataExtension ext = new NextwebDataExtension();
  
  private static void assertArrayEquals(final Object[] expecteds, final Object[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final byte[] expecteds, final byte[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final char[] expecteds, final char[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final short[] expecteds, final short[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final int[] expecteds, final int[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final long[] expecteds, final long[] actuals) {
    Assert.assertArrayEquals(expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final Object[] expecteds, final Object[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final byte[] expecteds, final byte[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final char[] expecteds, final char[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final short[] expecteds, final short[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final int[] expecteds, final int[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final String message, final long[] expecteds, final long[] actuals) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals);
  }
  
  private static void assertArrayEquals(final double[] expecteds, final double[] actuals, final double delta) {
    Assert.assertArrayEquals(expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final float[] expecteds, final float[] actuals, final float delta) {
    Assert.assertArrayEquals(expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final String message, final double[] expecteds, final double[] actuals, final double delta) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }
  
  private static void assertArrayEquals(final String message, final float[] expecteds, final float[] actuals, final float delta) throws ArrayComparisonFailure {
    Assert.assertArrayEquals(message, expecteds, actuals, delta);
  }
  
  private static void assertEquals(final Object expected, final Object actual) {
    Assert.assertEquals(expected, actual);
  }
  
  private static void assertEquals(final long expected, final long actual) {
    Assert.assertEquals(expected, actual);
  }
  
  private static void assertEquals(final String message, final Object expected, final Object actual) {
    Assert.assertEquals(message, expected, actual);
  }
  
  private static void assertEquals(final String message, final long expected, final long actual) {
    Assert.assertEquals(message, expected, actual);
  }
  
  private static void assertEquals(final double expected, final double actual, final double delta) {
    Assert.assertEquals(expected, actual, delta);
  }
  
  private static void assertEquals(final float expected, final float actual, final float delta) {
    Assert.assertEquals(expected, actual, delta);
  }
  
  private static void assertEquals(final String message, final double expected, final double actual, final double delta) {
    Assert.assertEquals(message, expected, actual, delta);
  }
  
  private static void assertEquals(final String message, final float expected, final float actual, final float delta) {
    Assert.assertEquals(message, expected, actual, delta);
  }
  
  private static void assertFalse(final boolean condition) {
    Assert.assertFalse(condition);
  }
  
  private static void assertFalse(final String message, final boolean condition) {
    Assert.assertFalse(message, condition);
  }
  
  private static void assertNotEquals(final Object first, final Object second) {
    Assert.assertNotEquals(first, second);
  }
  
  private static void assertNotEquals(final long first, final long second) {
    Assert.assertNotEquals(first, second);
  }
  
  private static void assertNotEquals(final String message, final Object first, final Object second) {
    Assert.assertNotEquals(message, first, second);
  }
  
  private static void assertNotEquals(final String message, final long first, final long second) {
    Assert.assertNotEquals(message, first, second);
  }
  
  private static void assertNotEquals(final double first, final double second, final double delta) {
    Assert.assertNotEquals(first, second, delta);
  }
  
  private static void assertNotEquals(final String message, final double first, final double second, final double delta) {
    Assert.assertNotEquals(message, first, second, delta);
  }
  
  private static void assertNotNull(final Object object) {
    Assert.assertNotNull(object);
  }
  
  private static void assertNotNull(final String message, final Object object) {
    Assert.assertNotNull(message, object);
  }
  
  private static void assertNotSame(final Object unexpected, final Object actual) {
    Assert.assertNotSame(unexpected, actual);
  }
  
  private static void assertNotSame(final String message, final Object unexpected, final Object actual) {
    Assert.assertNotSame(message, unexpected, actual);
  }
  
  private static void assertNull(final Object object) {
    Assert.assertNull(object);
  }
  
  private static void assertNull(final String message, final Object object) {
    Assert.assertNull(message, object);
  }
  
  private static void assertSame(final Object expected, final Object actual) {
    Assert.assertSame(expected, actual);
  }
  
  private static void assertSame(final String message, final Object expected, final Object actual) {
    Assert.assertSame(message, expected, actual);
  }
  
  private static <T extends Object> void assertThat(final T actual, final Matcher<? super T> matcher) {
    Assert.<T>assertThat(actual, matcher);
  }
  
  private static <T extends Object> void assertThat(final String reason, final T actual, final Matcher<? super T> matcher) {
    Assert.<T>assertThat(reason, actual, matcher);
  }
  
  private static void assertTrue(final boolean condition) {
    Assert.assertTrue(condition);
  }
  
  private static void assertTrue(final String message, final boolean condition) {
    Assert.assertTrue(message, condition);
  }
  
  private static void fail() {
    Assert.fail();
  }
  
  private static void fail(final String message) {
    Assert.fail(message);
  }
  
  private static <T extends Object, U extends T> void operator_doubleArrow(final T actual, final U expected) {
    Assert.assertEquals(expected, actual);
  }
  
  private static <T extends Exception> void isThrownBy(final Class<T> expected, final Procedure0 block) {
    try {
    	block.apply();
    	Assert.fail("Expected a " + expected.getName());
    } catch (Exception e) {
    	Class<?> actual = e.getClass();
    	Assert.assertTrue(
    		"Expected a " + expected.getName() + " but got " + actual.getName(), 
    		expected.isAssignableFrom(actual)
    	);
    }
  }
}
